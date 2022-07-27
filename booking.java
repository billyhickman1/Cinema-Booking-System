import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class booking extends JFrame implements ActionListener
{
	JTextField seat, totalcost;
	Choice type, block, time; // Choices for the GUI
	JLabel typelbl, blocklbl, timelbl, lblbl, mblbl, rblbl, inputseatlbl, totallbl, title;
	JButton submitbut, showbut, paybut;
	String selectedtime, total, selectedseat, selectedblock, tickettype;
	int timechoice, convertedseat1, convertedseat2, totalprice;
	int totaltickets = 0, currenti;

	Seat leftside1[][] = new Seat [4] [4];
	Seat midside1[][] = new Seat [4] [4];
	Seat rightside1[][] = new Seat [4] [4]; // Each one of these creates an array for each block

	Seat leftside3[][] = new Seat [4] [4];
	Seat midside3[][] = new Seat [4] [4];
	Seat rightside3[][] = new Seat [4] [4];

	Seat leftside5[][] = new Seat [4] [4];
	Seat midside5[][] = new Seat [4] [4];
	Seat rightside5[][] = new Seat [4] [4];

	Seat leftside7[][] = new Seat [4] [4];
	Seat midside7[][] = new Seat [4] [4];
	Seat rightside7[][] = new Seat [4] [4];

	Seat leftside9[][] = new Seat [4] [4];
	Seat midside9[][] = new Seat [4] [4];
	Seat rightside9[][] = new Seat [4] [4];

	Ticket tickets[] = new Ticket[30]; //Array for storing the tickets booked

	booking()
	{
		setSize(800,500);
		setLocation(200,200);
		setResizable(false);
		setLayout(new FlowLayout());
		ImageIcon imgtitle = new ImageIcon("title.gif");
		title = new JLabel(imgtitle);
		typelbl = new JLabel("Ticket Type: ");
		typelbl.setFont(new Font("Verdana", Font.BOLD, 22));
		type = new Choice();
		type.add("Adult");
		type.add("Child");
		type.add("OAP");
		blocklbl = new JLabel("Block:");
		blocklbl.setFont(new Font("Verdana", Font.BOLD, 22));
		block = new Choice();
		block.add("Left Block");
		block.add("Middle Block");
		block.add("Right Block");
		timelbl = new JLabel("Time: ");
		timelbl.setFont(new Font("Verdana", Font.BOLD, 22));
		time = new Choice();
		time.add("13:00");
		time.add("15:00");
		time.add("17:00");
		time.add("19:00");
		time.add("21:00");
		paybut = new JButton("Pay");
		paybut.setBackground(Color.white);
		ImageIcon lbimg = new ImageIcon("lblogo.gif");
		lblbl = new JLabel (lbimg);
		ImageIcon mbimg = new ImageIcon("mblogo.gif");
		mblbl = new JLabel (mbimg);
		ImageIcon rbimg = new ImageIcon("rblogo.gif");
		rblbl = new JLabel (rbimg);
		inputseatlbl = new JLabel ("Enter Seat Num: ");
		inputseatlbl.setFont(new Font("Verdana", Font.BOLD, 22));
		seat = new JTextField(2);
		seat.setFont(new Font("Verdana", Font.BOLD, 20));
		seat.setHorizontalAlignment(seat.CENTER);
		totallbl = new JLabel ("Total Price: ");
		totallbl.setFont(new Font("Verdana", Font.BOLD, 22));
		totalcost = new JTextField(7);
		totalcost.setFont(new Font("Verdana", Font.BOLD, 22));
		totalcost.setHorizontalAlignment(totalcost.CENTER);
		totalcost.setEditable(false);
		submitbut = new JButton("Submit");
		submitbut.setBackground(Color.white);
		showbut = new JButton("Show");
		showbut.setBackground(Color.white);

		/* The following delcarations
		initialise the variables for
		the positions of the blocks */

		int xleft = 0;
		int yleft = 0;
		int xmid = 0;
		int ymid = 0;
		int xright = 0;
		int yright = 0;


		xleft = 50;
		yleft = 310; //Initial positions
		int totalleftside1 = 0; //'Total' is the total and is used to label the seats

		for (int i = 0; i < leftside1.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				leftside1[i][j]=new Seat(totalleftside1, 0, xleft, yleft);
				xleft += 50; //Moving the next seat along the x position by 50
				totalleftside1 ++; //Adding one to the total for the next seat
			}
			xleft = 50; //Moving back to the left for the next row
			yleft += 30; //Moving down for the next row
		}

		xmid = 300;
		ymid = 310; //Initial positions
		int totalmidside1 = 0; //'Total' is the total and is used to label the seats
		for (int i = 0; i < midside1.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				midside1[i][j]=new Seat(totalmidside1, 0, xmid, ymid);
				xmid += 50; //Moving the next seat along the x position by 50
				totalmidside1 ++; //Adding one to the total for the next seat
			}
			xmid = 300; //Moving back to the left for the next row
			ymid += 30;  //Moving down for the next row
		}

		xright = 550;
		yright = 310; //Initial positions
		int totalrightside1 = 0; //'Total' is the total and is used to label the seats
		for (int i = 0; i < rightside1.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				rightside1[i][j]=new Seat(totalrightside1, 0, xright, yright);
				xright += 50; //Moving the next seat along the x position by 50
				totalrightside1 ++; //Adding one to the total for the next seat
			}
			xright = 550; //Moving back to the left for the next row
			yright += 30; //Moving down for the next row
		}

		/* The next lines of code perform the same processes as the ones before such as positions for each block */

		xleft = 50;
		yleft = 310;
		int totalleftside3 = 0;

		for (int i = 0; i < leftside3.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				leftside3[i][j]=new Seat(totalleftside3, 0, xleft, yleft);
				xleft += 50;
				totalleftside3 ++;
			}
			xleft = 50;
			yleft += 30;
		}

		xmid = 300;
		ymid = 310;
		int totalmidside3 = 0;
		for (int i = 0; i < midside3.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				midside3[i][j]=new Seat(totalmidside3, 0, xmid, ymid);
				xmid += 50;
				totalmidside3 ++;
			}
			xmid = 300;
			ymid += 30;
		}

		xright = 550;
		yright = 310;
		int totalrightside3 = 0;
		for (int i = 0; i < rightside3.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				rightside3[i][j]=new Seat(totalrightside3, 0, xright, yright);
				xright += 50;
				totalrightside3 ++;
			}
			xright = 550;
			yright += 30;
		}


		xleft = 50;
		yleft = 310;
		int totalleftside5 = 0;

		for (int i = 0; i < leftside5.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				leftside5[i][j]=new Seat(totalleftside5, 0, xleft, yleft);
				xleft += 50;
				totalleftside5 ++;
			}
			xleft = 50;
			yleft += 30;
		}

		xmid = 300;
		ymid = 310;
		int totalmidside5 = 0;
		for (int i = 0; i < midside5.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				midside5[i][j]=new Seat(totalmidside5, 0, xmid, ymid);
				xmid += 50;
				totalmidside5 ++;
			}
			xmid = 300;
			ymid += 30;
		}

		xright = 550;
		yright = 310;
		int totalrightside5 = 0;
		for (int i = 0; i < rightside5.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				rightside5[i][j]=new Seat(totalrightside5, 0, xright, yright);
				xright += 50;
				totalrightside5 ++;
			}
			xright = 550;
			yright += 30;
		}


		xleft = 50;
		yleft = 310;
		int totalleftside7 = 0;

		for (int i = 0; i < leftside7.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				leftside7[i][j]=new Seat(totalleftside7, 0, xleft, yleft);
				xleft += 50;
				totalleftside7 ++;
			}
			xleft = 50;
			yleft += 30;
		}

		xmid = 300;
		ymid = 310;
		int totalmidside7 = 0;
		for (int i = 0; i < midside7.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				midside7[i][j]=new Seat(totalmidside7, 0, xmid, ymid);
				xmid += 50;
				totalmidside7 ++;
			}
			xmid = 300;
			ymid += 30;
		}

		xright = 550;
		yright = 310;
		int totalrightside7 = 0;
		for (int i = 0; i < rightside7.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				rightside7[i][j]=new Seat(totalrightside7, 0, xright, yright);
				xright += 50;
				totalrightside7 ++;
			}
			xright = 550;
			yright += 30;
		}

		xleft = 50;
		yleft = 310;
		int totalleftside9 = 0;

		for (int i = 0; i < leftside9.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				leftside9[i][j]=new Seat(totalleftside9, 0, xleft, yleft);
				xleft += 50;
				totalleftside9 ++;
			}
			xleft = 50;
			yleft += 30;
		}

		xmid = 300;
		ymid = 310;
		int totalmidside9 = 0;
		for (int i = 0; i < midside9.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				midside9[i][j]=new Seat(totalmidside9, 0, xmid, ymid);
				xmid += 50;
				totalmidside9 ++;
			}
			xmid = 300;
			ymid += 30;
		}

		xright = 550;
		yright = 310;
		int totalrightside9 = 0;
		for (int i = 0; i < rightside9.length; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				rightside9[i][j]=new Seat(totalrightside9, 0, xright, yright);
				xright += 50;
				totalrightside9 ++;
			}
			xright = 550;
			yright += 30;
		}
		/* Adding the labels and text boxes to the screen */

		getContentPane().add(title);
		getContentPane().add(typelbl);
		getContentPane().add(type);
		getContentPane().add(blocklbl);
		getContentPane().add(block);
		getContentPane().add(timelbl);
		getContentPane().add(time);
		getContentPane().add(showbut);
		getContentPane().add(inputseatlbl);
		getContentPane().add(seat);
		getContentPane().add(submitbut);
		getContentPane().add(totallbl);
		getContentPane().add(totalcost);
		getContentPane().add(paybut);
		getContentPane().add(lblbl);
		getContentPane().add(mblbl);
		getContentPane().add(rblbl);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Color mycolor = new Color(255,255,255);
		getContentPane().setBackground(mycolor);

		submitbut.addActionListener(this);
		showbut.addActionListener(this); //Action listener for each individual button
		paybut.addActionListener(this);
	}
	public void paint(Graphics graf)
	{
		super.paint(graf);

		switch(timechoice)
		{
			case 1:
				for (int i = 0; i < leftside1.length; i++) //Looping throw the leftside at 1 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside1[i][j].display(graf); //Displaying the left side @ 1
						midside1[i][j].display(graf); //Displaying the mid side @ 1
						rightside1[i][j].display(graf); //Displaying the right side @ 1
					}
				}
				break;
			case 3:
				for (int i = 0; i < leftside3.length; i++) //Looping through the leftside at 3 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside3[i][j].display(graf); //Displaying the left side @ 3
						midside3[i][j].display(graf); //Displaying the mid side @ 3
						rightside3[i][j].display(graf); //Displaying the right side @ 3
					}
				}
				break;
			case 5:
				for (int i = 0; i < leftside5.length; i++) //Looping through the leftside at 5 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside5[i][j].display(graf); //Displaying the left side @ 5
						midside5[i][j].display(graf); //Displaying the mid side @ 5
						rightside5[i][j].display(graf); //Displaying the right side @ 5
					}
				}
				break;
			case 7:
				for (int i = 0; i < leftside7.length; i++) //Looping through the leftside at 7 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside7[i][j].display(graf); //Displaying the left side @ 7
						midside7[i][j].display(graf); //Displaying the mid side @ 7
						rightside7[i][j].display(graf); //Displaying the right side @ 7
					}
				}
				break;
			case 9:
				for (int i = 0; i < leftside9.length; i++) //Looping through the leftside at 7 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside9[i][j].display(graf); //Displaying the left side @ 7
						midside9[i][j].display(graf); //Displaying the mid side @ 7
						rightside9[i][j].display(graf); //Displaying the right side @ 7
					}
				}
				break;
			default:
			/* Used as what appears when the program starts */
				for (int i = 0; i < leftside1.length; i++) //Looping through the leftside at 1 block - gives initial size
				{
					for (int j = 0; j < 4; j++) //Looping through each row fully
					{
						leftside1[i][j].display(graf); //Displaying the left side @ 1
						midside1[i][j].display(graf); //Displaying the mid side @ 1
						rightside1[i][j].display(graf); //Displaying the right side @ 1
					}
				}
				break;
		}
	}
	public void actionPerformed(ActionEvent ev)
	{
		selectedblock = block.getSelectedItem(); //The values neeeded to book seats and check for availability taken from text boxes
		selectedtime = time.getSelectedItem();
		tickettype = type.getSelectedItem();

		if (ev.getSource() == submitbut)
		{
			selectedseat = seat.getText();
			int selectseat = Integer.parseInt(selectedseat); //Getting the seat number and converting it to an integer

			/* The following set of if statements checks for seat number and converts it
			to an X, Y position which can be used for booking */

			if (selectseat == 0)
				{convertedseat1 = 0;
				convertedseat2 = 0;}
			if (selectseat == 1)
				{convertedseat1 = 0;
				convertedseat2 = 1;}
			if (selectseat == 2)
				{convertedseat1 = 0;
				convertedseat2 = 2;}
			if (selectseat == 3)
				{convertedseat1 = 0;
				convertedseat2 = 3;}
			if (selectseat == 4)
				{convertedseat1 = 1;
				convertedseat2 = 0;}
			if (selectseat == 5)
				{convertedseat1 = 1;
				convertedseat2 = 1;}
			if (selectseat == 6)
				{convertedseat1 = 1;
				convertedseat2 = 2;}
			if (selectseat == 7)
				{convertedseat1 = 1;
				convertedseat2 = 3;}
			if (selectseat == 8)
				{convertedseat1 = 2;
				convertedseat2 = 0;}
			if (selectseat == 9)
				{convertedseat1 =2;
				convertedseat2 = 1;}
			if (selectseat == 10)
				{convertedseat1 = 2;
				convertedseat2 = 2;}
			if (selectseat == 11)
				{convertedseat1 = 2;
				convertedseat2 = 3;}
			if (selectseat == 12)
				{convertedseat1 = 3;
				convertedseat2 = 0;}
			if (selectseat == 13)
				{convertedseat1 = 3;
				convertedseat2 = 1;}
			if (selectseat == 14)
				{convertedseat1 = 3;
				convertedseat2 = 2;}
			if (selectseat == 15)
				{convertedseat1 = 3;
				convertedseat2 = 3;}


				if (selectedtime == "13:00") //If they are trying to book a ticket a 1 o'clock
				{
				timechoice = 1;
				repaint();
				if (selectedblock == "Left Block") // If they are trying to book a seat in the left block
				{
					int iftaken = leftside1[convertedseat1][convertedseat2].isTaken(); //Uses the istaken() method to see if it is taken
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked"); //Seat is taken and outputs an error message
					}
					else if (iftaken == 0)
					{
						leftside1[convertedseat1][convertedseat2].setSeat(); //Sets the seat to booked
						repaint(); //Re-displays it
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime); //Creates a new ticket objects in the tickets array
						int tempprice = tickets[totaltickets].getSeatPrice(); //Gets the seat price using the getSeatPrice() method
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice)); //Updates and adds to the text box
						totaltickets += 1; //Updates the total amount of tickets booked
						seat.setText("");
					}
				}
				if (selectedblock == "Middle Block")  // If they are trying to book a seat in the middle block
				{
					int iftaken = midside1[convertedseat1][convertedseat2].isTaken(); //Uses the istaken() method to see if it is taken
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked"); //Seat is taken and outputs an error message
					}
					else if (iftaken == 0)
					{
						midside1[convertedseat1][convertedseat2].setSeat(); //Sets the seat to booked
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime); //Creates a new ticket objects in the tickets array
						int tempprice = tickets[totaltickets].getSeatPrice();  //Gets the seat price using the getSeatPrice() method
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice)); //Updates and adds to the text box
						totaltickets += 1; //Updates the total amount of tickets booked
						seat.setText("");
					}
				}
				if (selectedblock == "Right Block")  // If they are trying to book a seat in the right block
				{
					int iftaken = rightside1[convertedseat1][convertedseat2].isTaken(); //Uses the istaken() method to see if it is taken
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");  //Seat is taken and outputs an error message
					}
					else if (iftaken == 0)
					{
						rightside1[convertedseat1][convertedseat2].setSeat(); //Sets the seat to booked
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);  //Creates a new ticket objects in the tickets array
						int tempprice = tickets[totaltickets].getSeatPrice(); //Gets the seat price using the getSeatPrice() method
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice)); //Updates and adds to the text box
						totaltickets += 1; //Updates the total amount of tickets booked
						seat.setText("");
					}
				}
			}

			/* Code is replicated for each of the times the user wants to book*/

			if (selectedtime == "15:00")
			{
				timechoice = 3;
				repaint();

				if (selectedblock == "Left Block")
				{
					int iftaken = leftside3[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						leftside3[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Middle Block")
				{
					int iftaken = midside3[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						midside3[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Right Block")
				{
					int iftaken = rightside3[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						rightside3[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}

			}
			if (selectedtime == "17:00")
			{
				timechoice = 5;
				repaint();

				if (selectedblock == "Left Block")
				{
					int iftaken = leftside5[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						leftside5[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Middle Block")
				{
					int iftaken = midside5[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						midside5[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Right Block")
				{
					int iftaken = rightside5[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						rightside5[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
			}
			if (selectedtime == "19:00")
			{
				timechoice = 7;
				repaint();

				if (selectedblock == "Left Block")
				{
					int iftaken = leftside7[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						leftside7[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Middle Block")
				{
					int iftaken = midside7[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						midside7[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Right Block")
				{
					int iftaken = rightside7[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						rightside7[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
			}
			if (selectedtime == "21:00")
			{
				timechoice = 9;
				repaint();

				if (selectedblock == "Left Block")
				{
					int iftaken = leftside9[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						leftside9[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Middle Block")
				{
					int iftaken = midside9[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						midside9[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
				if (selectedblock == "Right Block")
				{
					int iftaken = rightside9[convertedseat1][convertedseat2].isTaken();
					if (iftaken == 1)
					{
						JOptionPane.showMessageDialog(null, "This seat is taken and cannot be booked");
					}
					else if (iftaken == 0)
					{
						rightside9[convertedseat1][convertedseat2].setSeat();
						repaint();
						tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock, selectedtime);
						int tempprice = tickets[totaltickets].getSeatPrice();
						totalprice = totalprice + tempprice;
						totalcost.setText("£"+ Integer.toString(totalprice));
						totaltickets += 1;
						seat.setText("");
					}
				}
			}
		}
		if (ev.getSource() == showbut)
		{
			/* If the user has clicked the show button it sets a variable and then repaints the desired time */

			if (selectedtime == "13:00")
			{
				timechoice = 1;
				repaint();
			}
			if (selectedtime == "15:00")
			{
				timechoice = 3;
				repaint();
			}
			if (selectedtime == "17:00")
			{
				timechoice = 5;
				repaint();
			}
			if (selectedtime == "19:00")
			{
				timechoice = 7;
				repaint();
			}
			if(selectedtime == "21:00")
			{
				timechoice = 9;
				repaint();
			}

		}
		if (ev.getSource() == paybut) //Pay button to generate tickets
		{

  				try{
  					FileWriter stream= new FileWriter("tickets.txt"); //Opening the tickets.txt file
  					BufferedWriter output = new BufferedWriter(stream);

					for (int i = 0; i < totaltickets; i++) //Looping through the number of tickets booked
					{
  						/* Getting the variables needed from methods related to the object */

  						int ticketnum = tickets[i].getSeatNum();
						int ticketprice = tickets[i].getSeatPrice();
						String tickettype = tickets[i].getType();
						String tickettime = tickets[i].getTime();
						String ticketblock = tickets[i].getBlock();
						String newline = System.getProperty("line.separator");

						output.write(newline);
						output.write("Ticket Details");
						output.write(newline);
						output.write(newline);
						output.write("Ticket Number: " + ticketnum); //Writing ticketnum
						output.write(newline);
						output.write("Ticket Price: " + ticketprice);
						output.write(newline);
						output.write("Ticket Type: " + tickettype);
						output.write(newline);
						output.write("Ticket Time: " + tickettime);
						output.write(newline);
						output.write("Ticket Block: " + ticketblock);
						output.write(newline);
						output.write("____________________________________________________________________________________");
						output.write(newline);
					}
					output.close();
  					}catch (Exception e){//Catch exception if any
  					System.err.println("Error: " + e.getMessage());}

  				JOptionPane.showMessageDialog(null, "These tickets have been printed out to the file 'tickets.txt'"); //Success message

  				for(int i=0; i < tickets.length; i++)
  				{
					tickets[i] = null; //Setting the tickets array values to null so new tickets can be booked
				}
				totaltickets = 0; //Resets totaltickets to 0
				totalprice = 0; //Resets totalprice to 0
				totalcost.setText(""); //Sets totalcost to an empty text field
				seat.setText(""); //Wipes seat number text field
		}
	}
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new booking();
			}
		});
		
	}
}
class Seat
{
	private final int boxheight = 30; //Height of the drawn text box
	private final int boxwidth = 50; //Width of the drawn text box
	private int seatnumber;
	private int seattaken;
	private int x;
	private int y;

	public Seat(int number, int taken, int xstart, int ystart)
	{
		seatnumber = number; //Setting the seatnumber
		seattaken = taken; //Setting whether it is taken
		x = xstart; //Display position
		y = ystart;
	}
	public void display(Graphics graf)
	{
		int xdraw, ydraw;
		switch (seattaken)
		{
		case 0:
			xdraw = x +  boxwidth;
			ydraw = y +  boxheight;
			graf.drawRect(x,y,boxwidth,boxheight);
			graf.drawString(Integer.toString(seatnumber),x+20,y+boxheight*3/4); //Painting the seat with the seat number printed inside
			break;
		case 1:
			xdraw = x +  boxwidth;
			ydraw = y +  boxheight;
			graf.drawRect(x,y,boxwidth,boxheight);
			String msg = "N/A";
			graf.drawString(msg,x+20,y+boxheight*3/4); //Painting the seat with N/A printed inside
			break;
		default:
			break;
		}

	}
	public int isTaken()
	{
		return seattaken;
	}
	public void setSeat()
	{
		seattaken = 1;
	}
}
class Ticket
{
	private int price;
	private String tickettype;
	private int seatnum;
	private String timeticket;
	private String blockticket;


	public Ticket (String type, int seatnumber, String block, String time)
	{
		seatnum = seatnumber; //Setting tickets seat number
		tickettype = type; //Setting ticket type
		blockticket = block; //Setting block
		timeticket = time; //Setting time

		if (blockticket == "Left Block")
		{

			if (type == "Adult")
			{
				price = 5; //Setting adult price
			}
			if (type == "OAP")
			{
				price = 3; //Setting OAP price
			}
			if (type == "Child")
			{
				price = 2;  //Setting child price
			}
		}

		if (blockticket == "Middle Block")
		{

			if (type == "Adult")
			{
				price = 10; //Setting adult price
			}
			if (type == "OAP")
			{
				price = 3; //Setting OAP price
			}
			if (type == "Child")
			{
				price = 5;  //Setting child price
			}
		}

		if (blockticket == "Right Block")
		{

			if (type == "Adult")
			{
				price = 5; //Setting adult price
			}
			if (type == "OAP")
			{
				price = 3; //Setting OAP price
			}
			if (type == "Child")
			{
				price = 2;  //Setting child price
			}
		}
	}
	/* The following get methods are used to print tickets */
	public int getSeatPrice()
	{
		return price;
	}
	public String getTime()
	{
		return timeticket;
	}
	public String getBlock()
	{
		return blockticket;
	}
	public String getType()
	{
		return tickettype;
	}
	public int getSeatNum()
	{
		return seatnum;
	}
}
