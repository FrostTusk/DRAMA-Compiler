package language.expressions;


public enum Instructions {

    HIA, BIG;


    /**
     * Helper method to build the instruction more efficiently
     * @param arguments the collection of arguments
     * @return the generation partial instruction as String
     */
    private static String construct(Object... arguments){
        StringBuilder output = new StringBuilder();
        int remaining = arguments.length;

        for (Object argument : arguments) {
            output.append(argument);
            remaining--;
            if (remaining > 0) {
                output.append(", ");
            }
        }
        return output.toString();
    }

    /**
     * Constructs an instruction and return the generated instruction
     * @param instruction the enumeration of possible Drama instructions
     * @param arguments the collection of arguments
     * @return the generated instruction as String
     */
    public static String construct(Instructions instruction, Object... arguments) {
        StringBuilder output = new StringBuilder(instruction.toString());
        output.append(' ');
        output.append(construct(arguments));
        return output.toString();
    }

    /**
     * Constructs an instruction and return the generated instruction with punctuation and interpretation
     * @param instruction the enumeration of possible Drama instructions
     * @param interpretation the
     * @param arguments the collection of arguments
     * @return the generated instruction as String
     */
    public static String construct(Instructions instruction, String interpretation, Object... arguments) {
        StringBuilder output = new StringBuilder(instruction.toString());
        output.append(interpretation);
        output.append(' ');
        output.append(construct(arguments));
        return output.toString();
    }
}

