package configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	private Properties properties;

	public ReadConfig() {
		File fn = new File("Data/config.properties");
		properties = new Properties();
		try {
			FileInputStream reader = new FileInputStream(fn);
			properties.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}

	public long getExplicitWait() {
		String explicitWait = properties.getProperty("explicitWait");
		if (explicitWait != null)
			return Long.parseLong(explicitWait);
		else
			throw new RuntimeException("explicitWait not specified in the Configuration.properties file.");
	}

	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getUsername() {
		String username = properties.getProperty("username");
		if (username != null)
			return username;
		else
			throw new RuntimeException("username not specified in the Configuration.properties file.");
	}
	
	public String getPassword() {
		String password = properties.getProperty("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("password not specified in the Configuration.properties file.");
	}

}
