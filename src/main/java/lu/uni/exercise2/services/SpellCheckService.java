package lu.uni.exercise2.services;

import lu.uni.exercise2.entities.SpellError;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpellCheckService {

    @PersistenceContext
    private EntityManager em;

    public List<SpellError> checkSpelling(String inputText) {
        clearPreviousErrors();

        List<SpellError> spellErrors = new ArrayList<>();

        try {
            JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
            List<RuleMatch> matches = langTool.check(inputText);

            for (RuleMatch match : matches) {
                String wrongWord = inputText.substring(match.getFromPos(), match.getToPos());
                String suggestion = match.getSuggestedReplacements().isEmpty() ? "" : match.getSuggestedReplacements().get(0);

                SpellError error = new SpellError(wrongWord, suggestion, 1);

                em.persist(error);

                spellErrors.add(error);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spellErrors;
    }

    private void clearPreviousErrors() {
        em.createQuery("DELETE FROM SpellError").executeUpdate();
    }

    public List<Object[]> getTopErrors(int limit) {
        String jpql = "SELECT e.wrongWord, e.correctWord, SUM(e.frequency) FROM SpellError e GROUP BY e.wrongWord, e.correctWord ORDER BY SUM(e.frequency) DESC";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}