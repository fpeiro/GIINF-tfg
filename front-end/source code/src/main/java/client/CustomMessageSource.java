/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 *
 * @author Felipe
 */
public class CustomMessageSource extends ReloadableResourceBundleMessageSource {

    public Set<Map.Entry<Object, Object>> getMessages(Locale locale) {
        return getMergedProperties(locale).getProperties().entrySet();
    }
}
