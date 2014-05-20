package it.strazz.faces.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 * Util class
 * @author f1l0
 */
@ApplicationScoped
public final class Strings {
    
    public static boolean isNotEmpty(String str) {
        return str!=null && !str.isEmpty();
    }
    
    public static String getTheme() {
        String theme = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("primefaces.THEME");
        if(isNotEmpty(theme))
            return theme;
        return "aristo";
    }
}
