package lu.uni.exercise2.beans;

import lu.uni.exercise2.entities.SpellError;
import lu.uni.exercise2.services.SpellCheckService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("spellCheckBean")
@SessionScoped
public class SpellCheckBean implements Serializable {
    private String inputText;
    private List<SpellError> spellErrors;
    private String spellingErrorsHtml;
    private boolean statisticsButtonEnabled = false;

    @EJB
    private SpellCheckService spellCheckService;

    public String checkSpelling() {
        if (inputText != null && !inputText.trim().isEmpty()) {
            spellErrors = spellCheckService.checkSpelling(inputText);
            formatSpellingErrors();

            statisticsButtonEnabled = true;
        } else {
            spellErrors = null;
            spellingErrorsHtml = "";
        }
        return "results";
    }

    public void ajaxCheckSpelling() {
        if (inputText != null && !inputText.trim().isEmpty()) {
            spellErrors = spellCheckService.checkSpelling(inputText);
            formatSpellingErrors();
        } else {
            spellErrors = null;
            spellingErrorsHtml = "";
        }
    }

    private void formatSpellingErrors() {
        if (spellErrors != null && !spellErrors.isEmpty()) {
            spellingErrorsHtml = spellErrors.stream()
                .map(error -> "<span style='color:red;'>" + error.getWrongWord() + "</span> - Suggested: " + error.getCorrectWord())
                .collect(Collectors.joining("<br/>"));
        } else {
            spellingErrorsHtml = "";
        }
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public List<SpellError> getSpellErrors() {
        return spellErrors;
    }

    public void setSpellErrors(List<SpellError> spellErrors) {
        this.spellErrors = spellErrors;
    }

    public String getSpellingErrorsHtml() {
        return spellingErrorsHtml;
    }

    public boolean isStatisticsButtonEnabled() {
        return statisticsButtonEnabled;
    }
}