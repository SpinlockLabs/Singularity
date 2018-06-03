package sh.spinlock.singularity.core.connection;

import java.util.Properties;

public class JdbcConnectionConfig extends ConnectionConfig {
    private String protocol;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private boolean ssl;

    public String uri() {
        StringBuilder str = new StringBuilder("jdbc:");
        str.append(protocol);
        str.append("://");
        str.append(host);
        str.append(':');
        str.append(port);
        if (isNotEmpty(database)) {
            str.append('/');
            str.append(database);
        }
        return str.toString();
    }

    public Properties props() {
        return new Properties() {{
            if (isNotEmpty(username)) setProperty("user", username);
            if (isNotEmpty(password)) setProperty("password", password);
            if (isNotEmpty(username)) setProperty("ssl", Boolean.toString(ssl));
        }};
    }

    public String getProtocol() {
        return protocol;
    }

    public JdbcConnectionConfig setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getHost() {
        return host;
    }

    public JdbcConnectionConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public JdbcConnectionConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public JdbcConnectionConfig setDatabase(String database) {
        this.database = database;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JdbcConnectionConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JdbcConnectionConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isSsl() {
        return ssl;
    }

    public JdbcConnectionConfig setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    private boolean isNotEmpty(String in) {
        return in != null && !in.isEmpty();
    }
}
