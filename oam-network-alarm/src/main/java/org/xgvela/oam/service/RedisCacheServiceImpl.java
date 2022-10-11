package org.xgvela.oam.service;

import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisCacheServiceImpl<T> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> hashRedisTemplate;

    public void setObj(String key, T value) {
        hashRedisTemplate.opsForValue().set(key,value);
    }

    public T getObj(String key) {
        return (T) hashRedisTemplate.opsForValue().get(key);
    }

    public boolean remObj(String key) {
        return hashRedisTemplate.delete(key);
    }

    public void setKey(String mapName, Map<String, T> modelMap) {
        HashOperations<String, String, T> hps =hashRedisTemplate.opsForHash();
        hps.putAll(mapName, modelMap);
    }

    public void udpateActiveAlarmDb(String key, List<String> removeRedisList, List<ActiveAlarm> addRedisList) {
        hashRedisTemplate.execute(pieceAlarmCallAlarmDb(key, removeRedisList, addRedisList));
    }

    private SessionCallback<List> pieceAlarmCallAlarmDb(String key, List<String> waitRemove, List<ActiveAlarm> waitAdd) {
        SessionCallback<List> callback = new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                waitRemove.forEach(e -> operations.opsForHash().delete(key, e));
                // TODO zx d
                waitAdd.forEach(e -> operations.opsForHash().put(key, e.getAlarmId(), e.getId() + "-" + e.getAlarmEventTime().getTime() + "-" + e.getAckState()));
                return operations.exec();
            }
        };
        return callback;
    }

    public void putSetKey(String key, List<String> waitRemove, List<String> waitAdd) {
        stringRedisTemplate.execute(pieceAlarmCall2(key, waitRemove, waitAdd));
    }

    private SessionCallback<List> pieceAlarmCall2(String key, List<String> waitRemove, List<String> waitAdd) {
        SessionCallback<List> callback = new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                waitRemove.forEach(e -> operations.opsForSet().remove(key, e));
                waitAdd.forEach(e -> operations.opsForSet().add(key, e));
                return operations.exec();
            }
        };
        return callback;
    }

    public List<String> getIdByCombineKey(String key, List<String> keySets){
        return (List<String>)hashRedisTemplate.execute(pieceAlarmCallMap(key, keySets));
    }

    private SessionCallback<List> pieceAlarmCallMap(String key, List<String> keySets) {
        SessionCallback<List> callback = new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                keySets.forEach(s -> operations.opsForHash().get(key, s));
                return operations.exec();
            }
        };
        return callback;
    }


    public List<Boolean> isMemberList(String key, List<String> keySets) {
        return (List<Boolean>)stringRedisTemplate.execute(pieceAlarmCall(key, keySets));
    }

    private SessionCallback<List> pieceAlarmCall(String key, List<String> keySets) {
        SessionCallback<List> callback = new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                keySets.forEach(s -> operations.opsForSet().isMember(key, s));
                return operations.exec();
            }
        };
        return callback;
    }

    public void setSetKey(String key, Set<String> value) {
        for(String str: value) {
            stringRedisTemplate.opsForSet().add(key, str);
        }
    }

    public void remSetKey(String key, String value) {
        stringRedisTemplate.opsForSet().remove(key, value);
    }

    public boolean remStringSet(String key) {
        return stringRedisTemplate.delete(key);
    }

    public Set<String> getSet(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public boolean isMember(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    public Map<String, T> getMapValue(String mapName) {
        HashOperations<String, String, T> hps = hashRedisTemplate.opsForHash();
        return hps.entries(mapName);
    }

    public void setMapValue(String mapName, String key, T value) {
        hashRedisTemplate.opsForHash().put(mapName, key, value);
    }

    public void remMapValue(String mapName, String key) {
        hashRedisTemplate.opsForHash().delete(mapName, key);
    }

    public void sendAlarmMessage(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }

}
