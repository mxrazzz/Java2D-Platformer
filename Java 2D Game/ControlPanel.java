package game;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**A class to create button GUI for the game*/

public class ControlPanel {
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JButton pauseButton;
    private JButton quitButton;
    private JButton playButton;
    private JButton muteButton;
    private JButton unmuteButton;

    public ControlPanel(Game game){

        //button to be able to pause the game
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level.stop();
                GameLevel.gameMusic.pause();
                CharacterController.jumpingSound.stop();
                CharacterController.bulletSound.stop();
                CharacterController.setCharacterState(1);
            }
        });

        //button to continue playing the game
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level.start();
                if (CharacterController.getMuteSound() != 1){
                    GameLevel.gameMusic.resume();

                }
                CharacterController.setCharacterState(0);
            }
        });

        //button to be able to quit the game
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //button to mute the game music
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLevel.gameMusic.pause();
                CharacterController.setMuteSound(1);
            }
        });

        //button to unmute the music
        unmuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLevel.gameMusic.resume();
                CharacterController.setMuteSound(0);
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
