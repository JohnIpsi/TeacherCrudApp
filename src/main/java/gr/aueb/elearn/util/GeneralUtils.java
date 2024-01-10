package gr.aueb.elearn.util;

import gr.aueb.elearn.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

    private GeneralUtils() {
    }

    public static boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Teacher> removeDuplicateTeachers(List<Teacher> teacherList) {
        List<Integer> existingIds = new ArrayList<>();
        List<Teacher> uniqueTeachersList = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            if (!existingIds.contains(teacher.getId())) {
                uniqueTeachersList.add(teacher);
                existingIds.add(teacher.getId());
            }
        }

        return uniqueTeachersList;
    }

}
