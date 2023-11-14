package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author wanfeng
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder.
     * Hint: look at the `join` function in Utils
     */
    static final File CAPERS_FOLDER = Utils.join(CWD, "capers");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() throws IOException {
        /* 设置可持久化环境
         * 如何目标目录和文件不存在, 就创建;
         * 否则, 跳过. */
        File dot_capers = Utils.join(CAPERS_FOLDER, ".capers");
        if (!dot_capers.exists()) {
            dot_capers.mkdir();
        }

        File dogs = Utils.join(dot_capers, "dogs");
        if (!dogs.exists()) {
            dogs.mkdir();
        }

        File story = Utils.join(dot_capers, "story");
        if (!story.exists()) {
            story.createNewFile();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // 将 text 写入 caper/.capers/story 文件
        File story = Utils.join(CAPERS_FOLDER, ".capers/story");

        String result = Utils.readContentsAsString(story);
        if (result.isEmpty()) {
            result = text;
        } else {
            result += '\n' + text;
        }

        System.out.print(result);

        Utils.writeContents(story, result);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog dog = new Dog(name, breed, age);

        System.out.print(dog.toString());

        dog.saveDog();
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog dog = Dog.fromFile(name);

        dog.haveBirthday();

        dog.saveDog();
    }
}
