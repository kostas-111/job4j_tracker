package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        double sumScore = 0;
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                count++;
                sumScore += s.score();
            }
        }
        return sumScore / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> averagePupil = new ArrayList<>();
        double sumScore = 0;
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                sumScore += s.score();
            }
            averagePupil.add(new Label(p.name(), sumScore / p.subjects().size()));
            sumScore = 0;
        }
        return averagePupil;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> averageSubj = new ArrayList<>();
        Map<String, Integer> subjScores = new LinkedHashMap<>();
        int count = pupils.size();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                subjScores.put(s.name(), subjScores.getOrDefault(s.name(), 0) + s.score());
            }
        }
        for (String key : subjScores.keySet()) {
            averageSubj.add(new Label(key, subjScores.get(key) / count));
        }
        return averageSubj;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> bestStud = new ArrayList<>();
        double sumScore = 0;
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                sumScore += s.score();
            }
            bestStud.add(new Label(p.name(), sumScore));
            sumScore = 0;
        }
        Collections.sort(bestStud, Comparator.naturalOrder());
        return bestStud.get(bestStud.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> bestSubj = new ArrayList<>();
        Map<String, Integer> subjSum = new LinkedHashMap<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                subjSum.put(s.name(), subjSum.getOrDefault(s.name(), 0) + s.score());
            }
        }
        for (String key : subjSum.keySet()) {
            bestSubj.add(new Label(key, subjSum.get(key)));
        }
        Collections.sort(bestSubj, Comparator.naturalOrder());
        return bestSubj.get(bestSubj.size() - 1);
    }
}