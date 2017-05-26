package com.ydj.smart.common.nosql;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

/**
 * 
 * @author : Ares.yi
 * @createTime : 2013-4-22 上午11:33:30
 * @version : 1.0
 * @description : (配置详看驱动API,参数值在于调试)
 * 
 */
public class MongoDBFactory implements FactoryBean<Object> {

	/** 数据库 */
	private Mongo mongo;

	/** 集合名（表名） */
	private String name;

	/** 是否自动重连接 */
	private boolean autoConnectRetry = true;

	/** 连接超时时间（毫秒） */
	private int connectTimeout = 3 * 60 * 1000;

	/** 等候一个可用连接的最长时间（毫秒） */
	private int maxWaitTime = 1 * 60 * 1000;

	/**
	 * The maximum amount of time in MS to spend retrying to open connection to
	 * the same server. Default is 0, which means to use the default 15s if
	 * autoConnectRetry is on.
	 */
	private int maxAutoConnectRetryTime = 25 * 1000;

	/**
	 * This flag controls the socket keep alive feature that keeps a connection
	 * alive through firewalls java.net.Socket.setKeepAlive(boolean) Default is
	 * false.
	 */
	private boolean socketKeepAlive = true;

	/**
	 * The socket timeout in milliseconds It is used for I/O socket read and
	 * write operations java.net.Socket.setSoTimeout(int) Default is 0 and means
	 * no timeout.
	 */
	private int socketTimeout = 3 * 60 * 1000;

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
		config();
	}

	private void config() {
		MongoOptions op = mongo.getMongoOptions();
		op.setAutoConnectRetry(autoConnectRetry);
		op.setConnectTimeout(connectTimeout);
		op.setMaxWaitTime(maxWaitTime);
		op.setMaxAutoConnectRetryTime(maxAutoConnectRetryTime);
		op.setSocketKeepAlive(socketKeepAlive);
		op.setSocketTimeout(socketTimeout);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutoConnectRetry() {
		return autoConnectRetry;
	}

	public void setAutoConnectRetry(boolean autoConnectRetry) {
		this.autoConnectRetry = autoConnectRetry;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public int getMaxAutoConnectRetryTime() {
		return maxAutoConnectRetryTime;
	}

	public void setMaxAutoConnectRetryTime(int maxAutoConnectRetryTime) {
		this.maxAutoConnectRetryTime = maxAutoConnectRetryTime;
	}

	public boolean isSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public DB getObject() throws Exception {
		Assert.notNull(mongo);
		Assert.notNull(name);
		return mongo.getDB(name);
	}

	public Class<DB> getObjectType() {
		return DB.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
