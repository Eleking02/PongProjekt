package ch.berufsbildungscenter.projekt;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

// Die Superklasse Formen. Diese wird an Ball, Player und Block weitergegeben.
// enthält Koordinaten grössen und alles nötige fürs Bild
public class Form implements Serializable{
	
	// Instanz Variabeln

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6238458624962174630L;

	// X Koordinate
    private int x;
    
    // Y Koordinate
    private int y;
    
    // Breite
    private int width;
    
    // Höhe
    private int heigth;
    
    // Bild Object. Wird Importiert: aus java.awt.Image
    private ImageIcon image;
    
    // Eien Id damit man jedes Object einzeln auswählen kann und zB. Eigene Farben verteilen kann.
    private int paddleID;
    
    // Der Pfad des Bildes. dieses Attribut ist für das Image Object nötig.
    private String Pfad;


    public Form(int paddleID, String pfad) {
		// setzen der id
		this.setPaddleID(paddleID);
		// Bildpfad für den Sprite
		this.setPfad(pfad);
		// erstellen des Image Objekt
		ImageIcon ii = new ImageIcon(this.getClass().getResource(this.getPfad()));
		this.setImage(ii);
		this.setWidth(this.getImage().getWidth(null));
		this.setHeigth(this.getImage().getHeight(null));
	}
    
    
    //Getter und Setter Methoden
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigth;
    }

    Image getImage()
    {
      return image.getImage();
    }

    Rectangle getRect()
    {
      return new Rectangle(x, y, 
          image.getImage().getWidth(null), image.getImage().getHeight(null));
      
    }

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setImage(ImageIcon imageIcon) {
		this.image = imageIcon;
	}

	public int getPaddleID() {
		return paddleID;
	}

	public void setPaddleID(int id) {
		this.paddleID = id;
		image = new ImageIcon(this.getClass().getResource("/player/" + paddleID + ".png"));
	}

	public String getPfad() {
		return Pfad;
	}

	public void setPfad(String pfad) {
		Pfad = pfad;
	}

}
