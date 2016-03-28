package play.java8.optional;

import java.util.Optional;

/**
 * http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
 *
 * @author inhee kim
 */
public class PlayOptional {
    public static void main(String[] args) {
        String strNull = null;
        Optional nullableOptional = Optional.ofNullable(strNull);
        System.out.println("nullableOption: " + nullableOptional.orElse("str is null"));


        Computer computer = new Computer();
        SoundCard soundCard = new SoundCard();
        soundCard.setCardName("Blast");
        computer.setSoundCard(Optional.ofNullable(soundCard));
        Optional<Computer> opComputer = Optional.of(computer);
        String cardName = opComputer.flatMap(Computer::getSoundCard).map(SoundCard::getCardName).orElse("cardName is null");
        System.out.println("cardName: " + cardName);

                /*.ifPresent(System.out::println);*/
                /*.orElse("soundcard is null");*/
    }
}

