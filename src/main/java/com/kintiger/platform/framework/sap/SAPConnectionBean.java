package com.kintiger.platform.framework.sap;

import org.apache.log4j.Logger;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class SAPConnectionBean {
	static Logger logger = Logger.getLogger(SAPConnectionBean.class);

	private String poolName;// 连接池别名

	private int maximumConnectionCount;// 最大连接数

	private String clientName;// SAP客户端(SAP集团)

	private String user;// 登陆用户

	private String password;// 登陆密码

	private String language;// 语言

	private String hostName;// SAP服务器名或IP地址

	private String sysnr;// SAP系统码

	private String group;// Name of the group of application servers

	private String byGroup;// 是否通过GROUP方式连接

	private String repositoryName;// 函数仓库名称

	private String funcName;// 函数名称

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public SAPConnectionBean() {

	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getMaximumConnectionCount() {
		return maximumConnectionCount;
	}

	public void setMaximumConnectionCount(int maximumConnectionCount) {
		this.maximumConnectionCount = maximumConnectionCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getSysnr() {
		return sysnr;
	}

	public void setSysnr(String sysnr) {
		this.sysnr = sysnr;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getByGroup() {
		return byGroup;
	}

	public void setByGroup(String byGroup) {
		this.byGroup = byGroup;
	}

	// 从连接池中获取SAP客户端连接
	public JCO.Client getSAPClientFromPool() {
		logger.debug("进入getSAPClientFromPool方法");
		JCO.Client sapclient = null;
		try {
			// 判断连接池是否存在
			JCO.Pool pool = null;
			pool = JCO.getClientPoolManager().getPool(poolName);
			if (pool == null) {
				// 未找到指定的连接池则增加一个连接池
				addClientPool();
				pool = JCO.getClientPoolManager().getPool(poolName);
			}
			int maxConnections = pool.getMaxConnections();
			int numUserd = pool.getNumUsed();
			if (maxConnections - numUserd < 1) {
				logger.debug("线程池中没有可用的客户端连接，调用直接创建连接函数getSAPClientDirect()");
				sapclient = this.getSAPClientDirect();
			} else {
				logger.debug("调用" + SAPConnectionBean.class
						+ "中的方法getSAPClientFromPool()从线程池中获取SAP客户端连接");
				sapclient = JCO.getClient(poolName);
			}
		} catch (JCO.Exception e) {
			throw new JCO.Exception(1, "t", e.getMessage());
			// logger.error("调用" + SAPConnectionBean.class
			// + "的方法getSAPClientFromPool出错：" + e.getMessage());
		}
		return sapclient;
	}

	// 直接获取SAP客户端连接
	public JCO.Client getSAPClientDirect() {
		JCO.Client sapclient = null;
		try {
			if ("1".equals(this.byGroup))
				sapclient = JCO.createClient(clientName,// SAP logon client
						user,// SAP logon user
						password,// SAP logon password
						language,// SAP logon language
						hostName,// Host name of the message server
						sysnr,// Name of the SAP system
						group// Name of the group of application servers
						);
			else
				sapclient = JCO.createClient(clientName, user, password,
						language, hostName, sysnr);
			sapclient.connect();
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP连接错误：" + e.getMessage());
		}
		return sapclient;
	}

	// 以组的方式直接链接SAP
	public JCO.Client getSAPClientDirectByGroup() {
		JCO.Client sapclient = null;
		try {
			sapclient = JCO.createClient(clientName,// SAP logon client
					user,// SAP logon user
					password,// SAP logon password
					language,// SAP logon language
					hostName,// Host name of the message server
					sysnr,// Name of the SAP system
					group// Name of the group of application servers
					);
			sapclient.connect();
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP连接错误：" + e.getMessage());
		}
		return sapclient;
	}

	public JCO.Function getFunction() {
		logger.debug("进入getFunction方法");
		if (this.funcName == null || this.funcName.equals(""))
			return null;
		JCO.Function func = null;
		try {
			IFunctionTemplate ft = this.getRepository().getFunctionTemplate(
					funcName);
			func = ft.getFunction();
		} catch (JCO.Exception e) {
			e.printStackTrace();
			logger.error("调用" + SAPConnectionBean.class + "的方法getFunction出错："
					+ e.getMessage());
		}
		return func;
	}

	public JCO.Function getFunction(JCO.Client client) {
		logger.debug("进入getFunction(client)方法,调用SAP接口" + funcName);
		if (this.funcName == null || this.funcName.equals(""))
			return null;
		JCO.Function func = null;
		try {
			JCO.Repository repository = new JCO.Repository(this.repositoryName,
					client);
			IFunctionTemplate ft = repository.getFunctionTemplate(funcName);
			func = ft.getFunction();
		} catch (JCO.Exception e) {
			e.printStackTrace();
			logger.error("调用" + SAPConnectionBean.class + "的方法getFunction出错："
					+ e.getMessage());
		}
		return func;
	}

	// 获取SAP的repository
	private JCO.Repository getRepository() {
		logger.debug("进入getRepository方法");
		JCO.Repository repository = null;
		try {
			JCO.Pool pool = JCO.getClientPoolManager().getPool(poolName);
			if (pool == null) {
				// 未找到指定的连接池则增加一个连接池
				addClientPool();
			}
			logger.debug("调用" + SAPConnectionBean.class
					+ "中方法getRepository获取SAP函数仓库");
			repository = new JCO.Repository(this.repositoryName, this.poolName);
		} catch (JCO.Exception e) {
			e.printStackTrace();
			logger.error("调用" + SAPConnectionBean.class + "的方法getRepository出错："
					+ e.getMessage());
		}
		return repository;
	}

	private void addClientPool() {
		logger.debug("向线程池" + this.poolName + "中增加一个新的SAP客户端连接");
		try {
			JCO.addClientPool(poolName, maximumConnectionCount, clientName,
					user, password, language, hostName, sysnr);
		} catch (JCO.Exception e) {
			throw new RuntimeException("SAP连接错误：" + e.getMessage());
		}

	}
}
