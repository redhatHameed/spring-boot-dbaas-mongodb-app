package dbaas.demo.mongodbapp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.cloud.bindings.Binding;
import org.springframework.cloud.bindings.Bindings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Configuration
public class MongoDBServiceBindingConfig {

    @Bean(name = "ServiceBindingMongoClient")
    public MongoClient createMongoClient() throws Exception {
        List<Binding> bindings = new Bindings().filterBindings("mongodb");
        Map<String, String> binding = bindings.get(0).getSecret();
        String un = binding.get("username");
        String pwd = binding.get("password");
        String database = binding.get("database");
        String host = binding.get("host");
        String srv = binding.get("srv");
        String options = binding.get("options");
        StringBuilder sb = new StringBuilder("mongodb://");
        if ("true".equals(srv)) {
            sb.setLength(0);
            sb.append("mongodb+srv://");
        }

        sb.append(encoding(un)).append(":").append(encoding(pwd)).append("@").append(host);

        String dbString = "";
        if (options != null && !"".equals(options)) {
            dbString = "?" + options;
        }
        if (database != null && !"".equals(database)) {
            dbString = "/" + database + dbString;
        } else if (options != null && !"".equals(options)) {
            //with options but no db => <host>/?options...
            dbString = "/" + dbString;
        }
        sb.append(dbString);

        ConnectionString connectionString = new ConnectionString(sb.toString());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    private String encoding(String str) throws Exception {
        return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
    }
}