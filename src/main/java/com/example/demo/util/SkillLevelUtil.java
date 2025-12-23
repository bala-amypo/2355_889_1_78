package com.example.demo.util;

public class SkillLevelUtil {
    
    public static int levelToInt(String level) {
        return switch (level.toUpperCase()) {
            case "BEGINNER" -> 1;
            case "INTERMEDIATE" -> 2;
            case "EXPERT" -> 3;
            default -> 0;
        };
    }
    
    public static boolean canMatchSkill(int volunteerLevel, int requiredLevel) {
        return volunteerLevel >= requiredLevel;
    }
}