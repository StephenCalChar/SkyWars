package Game;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.xml.stream.events.EndDocument;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.CardLayout;

public class SkyWarsGUI extends JFrame {

	//private JPanel contentPane;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JSplitPane splitPane;  // split the window in top and bottom
	private final JPanel topPanel;       // container panel for the top
	private final JPanel bottomPanel;    // container panel for the bottom
	private JPanel startUpPanel;
	private JPanel onePlayerPlayPanel;
	private JButton newOnePlayerGameButton;
	private JButton loadGameButton;
	private JButton quitGameButton;
	private JButton masterShipMode;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;
	private JButton saveButton;
	private JButton quitButton;
	private JLabel lblNewLabel;
	JLabel currentScoreLabel;
	SkyWars skyWars;
	private ImageIcon backgroundImage = new ImageIcon("src/Img/nebulaResized.png");
	private ImageIcon mastershipIcon = new  ImageIcon("src/Img/mastershipResize.png");
	private ImageIcon battlestarIcon = new  ImageIcon("src/Img/battlestarResized.png");
	private ImageIcon battleshooterIcon = new  ImageIcon("src/Img/Battleshooter.png");
	private ImageIcon battleCruiserIcon = new  ImageIcon("src/Img/Battlecruiser.png");
	private ImageIcon twoEnemies = new ImageIcon("src/Img/TwoEnemy.png");
	private ImageIcon threeEnemies = new ImageIcon("src/Img/ThreeEnemy.png");
	private ImageIcon fourPlusEnemies = new ImageIcon("src/Img/FourPlusEnemy.png");
	private JButton upLeftButton;
	private JButton upRightButton;
	private JButton downRightButton;
	private JButton downLeftButton;
	private String[] gameOverIcons = {"src/Img/G.png", "src/Img/A.png", "src/Img/M.png", "src/Img/E.png", "src/Img/O.png", "src/Img/V.png", "src/Img/E.png", "src/Img/R.png"};
	private JLabel mastershipModeLabel;
	private String[] modesString = {"Defensive", "Offensive"}; 
	private int modesIndex = 0;
	private JButton nextMoveButton;
	private JButton autoMoveButton;
	private JLabel lblNewLabel_1;
	private JButton newZeroPlayerGameButton;
	private CardLayout cardLayout;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkyWarsGUI frame = new SkyWarsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SkyWarsGUI() {
		//creates new instance of Skywars game and passes the GUI into it as a parameter.
		skyWars = new SkyWars(this);
		splitPane = new JSplitPane();
		//creates two panels to insert into the Splitpane.
		topPanel = new JPanel();         
		bottomPanel = new JPanel();
		setPreferredSize(new Dimension(550, 800));    
		//content pane holds all the components, set it as a grid layout with 1 column and 1 row.
		getContentPane().setLayout(new GridLayout()); 
		//add splitpane to the contentpane.
		getContentPane().add(splitPane);            
		//configures the splitPane
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
		splitPane.setDividerLocation(500);                    
		splitPane.setTopComponent(topPanel);                  
		splitPane.setBottomComponent(bottomPanel);            
		///Bottom control panel Card layout frames area.
		//Creates a new instance of the cardLayout class and applies it to the bottompanel frame.
		cardLayout = new CardLayout(0,0);
		bottomPanel.setLayout(cardLayout);
		//Creation of game Play panel and controls
		onePlayerPlayPanel = new JPanel();
		onePlayerPlayPanel.setLayout(null);
		bottomPanel.add(onePlayerPlayPanel, "2");
		//Creation of Start Up Panel. with 3 options
		startUpPanel = new JPanel();
		bottomPanel.add(startUpPanel, "1"); 
		//tells the program to startup showing the startuppanel (referenced as '1')
		cardLayout.show(bottomPanel, "1");

		masterShipMode = new JButton("MasterShip Mode:");
		masterShipMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// switches between offensive and defensive mode on click of button.
				skyWars.getGrid().switchModes();
				//GUI only logic switching between display texts.
				modesIndex = (modesIndex + 1) % modesString.length;
				mastershipModeLabel.setText(modesString[modesIndex]);
			}
		});
		masterShipMode.setFont(new Font("Tahoma", Font.BOLD, 13));
		masterShipMode.setBounds(143, 212, 164, 31);
		onePlayerPlayPanel.add(masterShipMode);

		upButton = new JButton("UP");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on up bottom click
				//danger is returning true or false............
				skyWars.getGrid().getMasterShip().moveUp(); 
			}
		});
		upButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		upButton.setBounds(225, 55, 94, 46);
		onePlayerPlayPanel.add(upButton);

		downButton = new JButton("DOWN");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on down button click
				skyWars.getGrid().getMasterShip().moveDown();
			}
		});
		downButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		downButton.setBounds(225, 111, 94, 46);
		onePlayerPlayPanel.add(downButton);

		rightButton = new JButton("RIGHT");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on right button click
				skyWars.getGrid().getMasterShip().moveRight();
			}
		});
		rightButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		rightButton.setBounds(329, 83, 85, 46);
		onePlayerPlayPanel.add(rightButton);

		leftButton = new JButton("LEFT");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on left button click
				skyWars.getGrid().getMasterShip().moveLeft();	
			}
		});
		leftButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		leftButton.setBounds(118, 83, 85, 46);
		onePlayerPlayPanel.add(leftButton);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//saves the current game
				skyWars.saveGame();
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		saveButton.setBounds(437, 139, 74, 46);
		onePlayerPlayPanel.add(saveButton);

		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//stops current instance of game and returns to main menu
				skyWars.getGrid().setGameOver(true);
				topPanel.removeAll();
				topPanel.repaint();
				cardLayout.show(bottomPanel, "1");
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		quitButton.setBounds(437, 197, 74, 46);

		onePlayerPlayPanel.add(quitButton);
		onePlayerPlayPanel.add(getLblNewLabel());
		onePlayerPlayPanel.add(getCurrentScoreLabel());
		onePlayerPlayPanel.add(getUpLeftButton());
		onePlayerPlayPanel.add(getUpRightButton());
		onePlayerPlayPanel.add(getDownRightButton());
		onePlayerPlayPanel.add(getDownLeftButton());
		onePlayerPlayPanel.add(getMastershipModeLabel());

		nextMoveButton = new JButton("Next Move");
		nextMoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//takes a turn
				skyWars.getGrid().mastershipTurn();
			}
		});
		nextMoveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		nextMoveButton.setBounds(10, 17, 120, 67);
		onePlayerPlayPanel.add(nextMoveButton);

		autoMoveButton = new JButton("Auto Move");
		autoMoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				skyWars.getGrid().autoMove();	
			}
		});
		autoMoveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		autoMoveButton.setBounds(10, 135, 120, 67);
		onePlayerPlayPanel.add(autoMoveButton);  
		startUpPanel.setLayout(null);

		//Creates New Game Button in startup Panel
		newOnePlayerGameButton = new JButton("One Player");
		newOnePlayerGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//creates a new one player game.
				skyWars.newOnePlayerGame();
				topPanel.setLayout(new GridLayout(skyWars.getGrid().getRows(), skyWars.getGrid().getColumns())); 
				//removes the startup panel and adds the game play panel
				cardLayout.show(bottomPanel, "2");
				loadOnePlayerButtons();
				currentScoreLabel.setText(Integer.toString(skyWars.getGrid().getTotalScore()));
			}
		});
		newOnePlayerGameButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		newOnePlayerGameButton.setBounds(180, 78, 134, 53);
		startUpPanel.add(newOnePlayerGameButton);


		//Creates Load Game button in StartUp Panel
		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Loads the saved game file.
				skyWars.loadGame();
				topPanel.setLayout(new GridLayout(skyWars.getGrid().getRows(), skyWars.getGrid().getColumns())); 
				updateGui(skyWars.getGrid());
				cardLayout.show(bottomPanel, "2");
				//gui only logic. Selects the correct controls for the type of game.
				if (skyWars.getGameType()== GameType.zeroPlayer) {
					loadZeroPlayerButtons();
				}
				else {
					loadOnePlayerButtons();
				}
				currentScoreLabel.setText(Integer.toString(skyWars.getGrid().getTotalScore()));
			}
		});
		loadGameButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		loadGameButton.setBounds(294, 171, 103, 53);
		startUpPanel.add(loadGameButton);

		quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//exits the game.
				System.exit(1);
			}
		});
		quitGameButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		quitGameButton.setBounds(407, 171, 103, 53);
		startUpPanel.add(quitGameButton);

		lblNewLabel_1 = new JLabel("New Game");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(125, 38, 103, 24);
		startUpPanel.add(lblNewLabel_1);

		newZeroPlayerGameButton = new JButton("Zero Player");
		newZeroPlayerGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Creates a new Computer Vs Computer Game.
				skyWars.newZeroPlayerGame();
				topPanel.setLayout(new GridLayout(skyWars.getGrid().getRows(), skyWars.getGrid().getColumns())); 
				//removes the startup panel and adds the game play panel controls for a 0 player game.
				cardLayout.show(bottomPanel, "2");
				loadZeroPlayerButtons();
				currentScoreLabel.setText(Integer.toString(skyWars.getGrid().getTotalScore()));
			}
		});
		newZeroPlayerGameButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		newZeroPlayerGameButton.setBounds(34, 78, 134, 53);
		startUpPanel.add(newZeroPlayerGameButton);
		//sizes frame
		pack(); 

	} //end constructor

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Current Score:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(388, 25, 110, 22);
		}
		return lblNewLabel;
	}
	private JLabel getCurrentScoreLabel() {
		if (currentScoreLabel == null) {
			currentScoreLabel = new JLabel("0");
			currentScoreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			currentScoreLabel.setBounds(462, 49, 19, 22);
		}
		return currentScoreLabel;
	}

	//Updates top half of the gui which is compromised of  grid of buttons.
	public void updateGui(Grid grid) {
		//clears current buttons in grid		
		this.topPanel.removeAll();
		for (int loop =0; loop <grid.getRows(); loop ++ ) 
		{
			for(int loop2=0; loop2 < grid.getColumns(); loop2++)
			{
				//if mastership is on square then it will appear on the tile.
				ArrayList<Ship> tempArrayList = grid.getTheRows().get(loop).getTheSquares().get(loop2).getTheShips();
				if(tempArrayList.contains(grid.getMasterShip())) {
					JButton mastershipButton = new JButton("", this.mastershipIcon);
					this.topPanel.add(mastershipButton);
				}
				else {
					//will only activate if the grid square does not contain the mastership
					int tempShipListSize = tempArrayList.size();
					if (tempShipListSize == 0)
					{
						//empty tile appears if there are no ships on the square at all
						JButton backgroundButton = new JButton("", this.backgroundImage);							
						this.topPanel.add(backgroundButton);	
					}
					// if an enemyship is on a tile then the ship's image appears on the tile.
					if (tempShipListSize == 1) {
						int firstIndex =0;
						if(tempArrayList.get(firstIndex).getVisual()== ShipType.battleStar) {
							JButton battlestarButton = new JButton("", this.battlestarIcon);
							this.topPanel.add(battlestarButton);	
						}
						if(tempArrayList.get(firstIndex).getVisual()== ShipType.battleShooter) {
							JButton battleshooterButton = new JButton("", this.battleshooterIcon);
							this.topPanel.add(battleshooterButton);	
						}
						if(tempArrayList.get(firstIndex).getVisual()== ShipType.battleCruiser) {
							JButton battlecruiserButton = new JButton("", this.battleCruiserIcon);
							this.topPanel.add(battlecruiserButton );	
						}

					}
					//This section will load tile images for squares which contain more than 1
					//enemy ship.
					if (tempShipListSize > 1)
					{
						switch(tempShipListSize) {
						case 2:
							JButton twoShipButton = new JButton("", this.twoEnemies);
							this.topPanel.add(twoShipButton);
							break;
						case 3:
							JButton threeEnemyButton = new JButton("", this.threeEnemies);
							this.topPanel.add(threeEnemyButton);
							break;
						default:
							JButton fourEnemyButton = new JButton("", this.fourPlusEnemies);
							this.topPanel.add(fourEnemyButton);
						} // End switch statement
					}
				}
			} //end inner for loop
		} //end outer for loop
		this.topPanel.revalidate();   
	} //end updateGUI Method

	private JButton getUpLeftButton() {
		if (upLeftButton == null) {
			upLeftButton = new JButton("");
			upLeftButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//moves the master ship diagonally upwards to the left
					skyWars.getGrid().getMasterShip().MoveUpLeft();
				}
			});
			upLeftButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			upLeftButton.setBounds(167, 27, 48, 46);
		}
		return upLeftButton;
	}

	private JButton getUpRightButton() {
		if (upRightButton == null) {
			upRightButton = new JButton("");
			upRightButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//moves the master ship diagonally upwards to the right
					skyWars.getGrid().getMasterShip().MoveUpRight();
				}
			});
			upRightButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			upRightButton.setBounds(329, 27, 48, 46);
		}
		return upRightButton;
	}

	private JButton getDownRightButton() {
		if (downRightButton == null) {
			downRightButton = new JButton("");
			downRightButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//moves the master ship diagonally downwards to the right
					skyWars.getGrid().getMasterShip().moveDownRight();
				}
			});
			downRightButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			downRightButton.setBounds(329, 139, 48, 46);
		}
		return downRightButton;
	}

	private JButton getDownLeftButton() {
		if (downLeftButton == null) {
			downLeftButton = new JButton("");
			downLeftButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//moves the master ship diagonally downwards to the left
					skyWars.getGrid().getMasterShip().moveDownLeft();
				}
			});
			downLeftButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			downLeftButton.setBounds(167, 139, 48, 46);
		}
		return downLeftButton;
	}

	//contains the methods which occur when the game is over
	public void gameOver() {
		cardLayout.show(bottomPanel, "1");
		this.topPanel.removeAll();
		printGameOverGrid();

	}

	//creates the buttons for the game over message on the top half of the Jpanel.
	public void printGameOverGrid(){
		this.topPanel.removeAll();
		int counter =0;
		for (int loop =0; loop <skyWars.getGrid().getRows(); loop ++ ) 
		{
			for(int loop2=0; loop2 < skyWars.getGrid().getColumns(); loop2++)
			{
				if(counter < gameOverIcons.length) {
					//all the image paths are saved in a string array and are added to each button through a loop.
					ImageIcon tempIcon = new ImageIcon(gameOverIcons[counter]);
					JButton backgroundButton = new JButton("", tempIcon);
					this.topPanel.add(backgroundButton);
				}
				else {
					JButton backgroundButton = new JButton(this.backgroundImage);
					this.topPanel.add(backgroundButton);
				}
				counter ++;
			}
		}
		this.topPanel.revalidate();  
	} //end print GameOverGrid


	private JLabel getMastershipModeLabel() {
		if (mastershipModeLabel == null) {
			mastershipModeLabel = new JLabel("Defensive");
			mastershipModeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			mastershipModeLabel.setBounds(317, 216, 110, 21);
		}
		return mastershipModeLabel;
	}

	//sets up all the buttons required for zero player game
	public void loadZeroPlayerButtons() {
		autoMoveButton.setVisible(true);
		autoMoveButton.setText("Auto Move");
		nextMoveButton.setVisible(true);
		upButton.setVisible(false);
		downButton.setVisible(false);
		rightButton.setVisible(false);
		leftButton.setVisible(false);
		upLeftButton.setVisible(false);
		upRightButton.setVisible(false);
		downLeftButton.setVisible(false);
		downRightButton.setVisible(false);
		saveButton.setVisible(true);
		quitButton.setVisible(true);


	}

	//sets up all the buttons required for one player game
	public void loadOnePlayerButtons() {
		autoMoveButton.setVisible(false);
		autoMoveButton.setText("Auto Move");
		nextMoveButton.setVisible(false);
		upButton.setVisible(true);
		downButton.setVisible(true);
		rightButton.setVisible(true);
		leftButton.setVisible(true);
		upLeftButton.setVisible(true);
		upRightButton.setVisible(true);
		downLeftButton.setVisible(true);
		downRightButton.setVisible(true);
		saveButton.setVisible(true);
		quitButton.setVisible(true);
	}


	//sets up only the buttons required to let the game auto run. Ensures the auto run cannot be interrupted.
	public void autoRunOnlyButtons() {
		nextMoveButton.setVisible(false);
		autoMoveButton.setText("Pause");
		saveButton.setVisible(false);
		quitButton.setVisible(false);	
	}	

} //end class


