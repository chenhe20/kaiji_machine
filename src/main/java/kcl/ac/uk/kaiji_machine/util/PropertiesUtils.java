package kcl.ac.uk.kaiji_machine.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Configuration
@PropertySource(value = {"classpath:application.yml"})
public class PropertiesUtils implements EnvironmentAware {

    private static Environment env;

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment env) {
        PropertiesUtils.env = env;
    }
}
