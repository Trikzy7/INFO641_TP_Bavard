import java.util.EventObject;

public class PapotageEvent extends EventObject {
    private String sujet;
    private String theme;
    private String corps;

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }

    public String getTheme() {
        return theme;
    }
    public PapotageEvent(Object source, String sujet, String theme, String corps) {
        super(source);
        this.sujet = sujet;
        this.theme = theme;
        this.corps = corps;
    }
}
