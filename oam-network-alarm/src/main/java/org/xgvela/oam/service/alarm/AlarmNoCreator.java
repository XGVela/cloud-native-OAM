package org.xgvela.oam.service.alarm;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

public class AlarmNoCreator {
    public static final String ALARM_PREFIX = "Threshold alarm";

    public static String createAlarmNo(JSONObject recordJson) {
        String neId = recordJson.getString("neId");
        String partOne = createPartOne(neId);
        String partTwo = createPartTwo(recordJson);
        return partOne + partTwo;
    }

    private static String createPartTwo(JSONObject recordJson) {
        String specificProblem = recordJson.getString("specificProblem");
        if (StringUtils.contains(specificProblem, ALARM_PREFIX)) {
            return "000009";
        }

        String specificProblemId = recordJson.getString("specificProblemId");
        switch (specificProblemId) {
            case "CertCloseExpired":
                return "000001";
            case "log_space_alarm":
                return "000002";
            case "dataDelay":
                return dataDelayNo(recordJson);
            case "NTP server exceeds the offset":
                return "000008";
            default:
                return neAlarmNo(specificProblemId);
        }
    }

    private static String neAlarmNo(String specificProblemId) {
        try {
            String[] s = specificProblemId.split("_");
            if (s.length != 2) {
                return "999999";
            }
            int superiorNo = Integer.parseInt(s[0]);
            return String.format("%06d", superiorNo);
        } catch (Exception e) {
            return "999999";
        }
    }

    private static String dataDelayNo(JSONObject recordJson) {
        String alarmId = recordJson.getString("alarmId");
        String timeUnit = alarmId.substring(alarmId.indexOf("_") + 1);
        switch (timeUnit) {
            case "5m":
                return "000003";
            case "15m":
                return "000004";
            case "30m":
                return "000005";
            case "1h":
                return "000006";
            default:
                return "000007";
        }
    }

    /**
     * @param neId
     * @return
     */
    private static String createPartOne(String neId) {
        return StringUtils.isEmpty(neId) ? "001" : "002";
    }
}
