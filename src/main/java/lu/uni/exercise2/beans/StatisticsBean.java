package lu.uni.exercise2.beans;

import lu.uni.exercise2.services.SpellCheckService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named("statisticsBean")
@RequestScoped
public class StatisticsBean {
    private int topX = 5;
    private List<Object[]> topErrors;

    @EJB
    private SpellCheckService spellCheckService;

    public void loadTopErrors() {
        topErrors = spellCheckService.getTopErrors(topX);
    }
    
    public List<Object[]> getTopErrors() {
        return topErrors;
    }

    public int getTopX() {
        return topX;
    }

    public void setTopX(int topX) {
        this.topX = topX;
    }

    public String getWrongWord(Object[] error) {
        return (String) error[0];
    }

    public String getCorrectWord(Object[] error) {
        return (String) error[1];
    }

    public Long getErrorCount(Object[] error) {
        return (Long) error[2];
    }
}