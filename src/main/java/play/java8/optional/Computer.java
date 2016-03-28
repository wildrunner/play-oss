package play.java8.optional;

import java.util.Optional;

/**
 * @author inhee kim
 */
public class Computer {
    private Optional<SoundCard> soundCard;


    public Optional<SoundCard> getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(Optional<SoundCard> soundCard) {
        this.soundCard = soundCard;
    }
}
