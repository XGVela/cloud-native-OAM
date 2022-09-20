package org.xgvela.oam.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.alibaba.fastjson.JSONObject;
import org.xgvela.oam.entity.log.RunLog;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author gengfei01
 *
 */
public class LogAppender extends AppenderBase<ILoggingEvent> {

	private String serverName = "cnet-alarm";

	private Logger logger = (Logger) LoggerFactory.getLogger("runLog");

	@Override
	protected void append(ILoggingEvent iLoggingEvent) {

		RunLog runLog = new RunLog();
		runLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		String message = iLoggingEvent.getMessage();
		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(iLoggingEvent.getTimeStamp()));
		runLog.setContent(timeStr + " [" + iLoggingEvent.getLevel().levelStr + "] " + iLoggingEvent.getLoggerName()
				+ " - " + message);
		runLog.setServerName(serverName);
		runLog.setLevel(iLoggingEvent.getLevel().levelStr);
		runLog.setCreateTime(new Date(iLoggingEvent.getTimeStamp()));

		try {
			runLog.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.error("get request ip error,error msg <{}>", e.getMessage(), e);
		}

		logger.info(JSONObject.toJSONString(runLog));
	}
}
