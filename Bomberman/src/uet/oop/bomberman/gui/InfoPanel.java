package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Panel hiển thị thông tin thời gian, điểm mà người chơi đạt được
 */
public class InfoPanel extends JPanel {
	
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel liveLabel;
	private JLabel speedLabel;
	private JLabel bombLabel;
	private JLabel flameLabel;

	public InfoPanel(Game game) {
		setLayout(new GridLayout());
		
		timeLabel = new JLabel("Time: " + game.getBoard().getTime());
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		pointsLabel = new JLabel("Points: " + game.getBoard().getPoints());
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);

		liveLabel = new JLabel("Live: " + game.getBoard().getLive());
		liveLabel.setForeground(Color.RED);
		liveLabel.setHorizontalAlignment(JLabel.CENTER);

		speedLabel = new JLabel("Speed: " + game.getBoard().getBomberSpeed());
		speedLabel.setForeground(Color.GREEN);
		speedLabel.setHorizontalAlignment(JLabel.CENTER);

		bombLabel = new JLabel("BombRate: " + game.getBoard().getBombRate());
		bombLabel.setForeground(Color.GREEN);
		bombLabel.setHorizontalAlignment(JLabel.CENTER);

		flameLabel = new JLabel("BombRadius: " + game.getBoard().getBombRadius());
		flameLabel.setForeground(Color.GREEN);
		flameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(timeLabel);
		add(pointsLabel);
		add(liveLabel);
		add(speedLabel);
		add(bombLabel);
		add(flameLabel);
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));
	}
	
	public void setTime(int t) {
		timeLabel.setText("Time: " + t);
	}

	public void setLive(int t) {
		liveLabel.setText("Live: " + t);
	}

	public void setPoints(int t) {
		pointsLabel.setText("Score: " + t);
	}

	public void setSpeed(double t){
		speedLabel.setText("Speed: " + t);
	}

	public void setBomb(int t){
		bombLabel.setText("BombRate: " + t);
	}

	public void setFlame(int t){
		flameLabel.setText("BombRadius: " + t);
	}
}
