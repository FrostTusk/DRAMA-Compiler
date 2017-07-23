package model;

import language.expressions.Expression;

// GNU General Public License

public enum Instructions {

    HIA, BIG, SPR, VSP, BST, STP, EINDPR;


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
     * Constructs an instruction and return the generated instruction with accumulator
     * @param instruction the enumeration of possible Drama instructions
     * @param interpretation the given interpretation
     * @param accumulator the accumulator
     * @param argument the argument
     * @return the constructed instruction as String
     */
    public static String construct(Instructions instruction, Interpretation interpretation, int accumulator, Expression argument ) {
        StringBuilder output = new StringBuilder(instruction.toString());
        output.append(" " + interpretation);
        output.append(" R" + accumulator);
        output.append(", " + argument.evaluate());
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
     * @param interpretation the interpretation
     * @param arguments the collection of arguments
     * @return the generated instruction as String
     */
    public static String construct(Instructions instruction, Interpretation interpretation, Object... arguments) {
        StringBuilder output = new StringBuilder(instruction.toString());
        output.append(interpretation);
        output.append(' ');
        output.append(construct(arguments));
        return output.toString();
    }

}

