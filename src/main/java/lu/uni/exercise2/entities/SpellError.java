package lu.uni.exercise2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "SpellError")
public class SpellError {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String wrongWord;
    
    @Column(nullable = false)
    private String correctWord;

    @Column(nullable = false)
    private int frequency;

    public SpellError() {
    }

    public SpellError(String wrongWord, String correctWord, int frequency) {
        this.wrongWord = wrongWord;
        this.correctWord = correctWord;
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWrongWord() {
        return wrongWord;
    }

    public void setWrongWord(String wrongWord) {
        this.wrongWord = wrongWord;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}