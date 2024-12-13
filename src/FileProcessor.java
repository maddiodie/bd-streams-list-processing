import listprocessing.ProjectServerManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileProcessor {

    private ProjectServerManager serverManager;

    /**
     * Constructor for FileProcessor.
     */
    public FileProcessor() {
        serverManager = new ProjectServerManager();
    }

    /**
     * Returns a <List<String>> that contains only the file names for .txt or .md files, all lowercase and
     * sorted alphabetically.
     * @param source Source list.
     * @return Processed list.
     */
    public List<String> filterDocs(List<String> source) {
        // filter for the files names using the contains method
        // map elements to lowercase
        // sort naturally

        return source.stream()
                .filter(fileName -> fileName.contains(".txt") || fileName.contains(".md"))
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns a <Set<String>> that contains only the file names for .java files, with each file
     * capitalized.
     * @param source Source List.
     * @return Processed Set.
     */
    public Set<String> filterJava(List<String> source) {
        // filter for .java files
        // map elements to lowercase
        return source.stream()
                .filter(fileName -> fileName.contains(".java"))
                .map(FileProcessor::toCapitalizeFirstLetter)
                .collect(Collectors.toSet());
    }

    /**
     * Helper method for <filterJava()>. Capitalizes the first letter of the String passed into the
     * method.
     * @param string String we're interested in capitalizing the first letter of.
     * @return The given String, but now with the first letter capitalized.
     */
    private static String toCapitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * Sorts all file names in the list, and submits them in order to the project server via the method
     * `submitToProject()` of the `listprocessing.ProjectServerManager` class.
     * @param source Source list.
     */
    public void sortAndSubmitAll(List<String> source) {
        // sort the files names
        // assign the collected stream to the project server using the <submitToProject()> method
        //  from the ProjectServerManager (which is an instance variable i think ...)

        List<String> result = source.stream()
                .sorted()
                .collect(Collectors.toList());

        for (String fileName : result) {
            serverManager.submitToProject(fileName);
        }

    }

}
