package vectordraw.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import vectordraw.models.ShapeFactory;
import vectordraw.models.interfaces.shape.IColor;

/**
 * This class Defines colours.
 * 
 */
public final class Colors  {
	public static final IColor TEAL			 = ShapeFactory.INST.createColor(0.0, 0.5, 0.5);
	public static final IColor LIME			 = ShapeFactory.INST.createColor(0.75, 1, 0);
	public static final IColor GREEN_YELLOW   = ShapeFactory.INST.createColorInt(216,255, 79);
	public static final IColor YELLOW         = ShapeFactory.INST.createColorInt(255,255,  0);
	public static final IColor GOLDEN_ROD     = ShapeFactory.INST.createColorInt(255,229, 40);
	public static final IColor DANDELION      = ShapeFactory.INST.createColorInt(255,181, 40);
	public static final IColor APRICOT        = ShapeFactory.INST.createColorInt(255,173,122);
	public static final IColor PEACH          = ShapeFactory.INST.createColorInt(216,127, 76);
	public static final IColor MELON          = ShapeFactory.INST.createColorInt(255,137,127);
	public static final IColor YELLOW_ORANGE  = ShapeFactory.INST.createColorInt(216,147,  0);
	public static final IColor ORANGE         = ShapeFactory.INST.createColorInt(255, 99, 33);
	public static final IColor BURNT_ORANGE   = ShapeFactory.INST.createColorInt(255,124,  0);
	public static final IColor BITTERSWEET    = ShapeFactory.INST.createColorInt(193,  2,  0);
	public static final IColor RED_ORANGE     = ShapeFactory.INST.createColorInt(255, 58, 33);
	public static final IColor MAHOGANY       = ShapeFactory.INST.createColorInt(165,  0,  0);
	public static final IColor MAROON         = ShapeFactory.INST.createColorInt(173,  0,  0);
	public static final IColor BRICKRED       = ShapeFactory.INST.createColorInt(183,  0,  0);
	public static final IColor RED            = ShapeFactory.INST.createColorInt(255,  0,  0);
	public static final IColor ORANGERED      = ShapeFactory.INST.createColorInt(255,  0,127);
	public static final IColor RUBINERED      = ShapeFactory.INST.createColorInt(255,  0,221);
	public static final IColor WILDSTRAWBERRY = ShapeFactory.INST.createColorInt(255, 10,155);
	public static final IColor SALMON         = ShapeFactory.INST.createColorInt(255,119,158);
	public static final IColor CARNATIONPINK  = ShapeFactory.INST.createColorInt(255, 94,255);
	public static final IColor MAGENTA        = ShapeFactory.INST.createColorInt(255,  0,255);
	public static final IColor VIOLETRED      = ShapeFactory.INST.createColorInt(255, 48,255);
	public static final IColor RHODAMINE      = ShapeFactory.INST.createColorInt(255, 45,255);
	public static final IColor MULBERRY       = ShapeFactory.INST.createColorInt(163, 20,149);
	public static final IColor REDVIOLET      = ShapeFactory.INST.createColorInt(150,  0,168);
	public static final IColor FUSHIA         = ShapeFactory.INST.createColorInt(114,  2,234);
	public static final IColor LAVENDER       = ShapeFactory.INST.createColorInt(255,132,255);
	public static final IColor THISTLE        = ShapeFactory.INST.createColorInt(224,104,255);
	public static final IColor ORCHID         = ShapeFactory.INST.createColorInt(173, 91,255);
	public static final IColor DARKORCHID     = ShapeFactory.INST.createColorInt(153, 51,204);
	public static final IColor PURPLE         = ShapeFactory.INST.createColorInt(140, 35,255);
	public static final IColor PLUM           = ShapeFactory.INST.createColorInt(127,  0,255);
	public static final IColor VIOLET         = ShapeFactory.INST.createColorInt( 53, 30,255);
	public static final IColor ROYALPURPLE    = ShapeFactory.INST.createColorInt( 63, 25,255);
	public static final IColor BLUEVIOLET     = ShapeFactory.INST.createColorInt( 25, 12,244);
	public static final IColor PERIWINKLE     = ShapeFactory.INST.createColorInt(109,114,255);
	public static final IColor CADETBLUE      = ShapeFactory.INST.createColorInt(140, 35,255);
	public static final IColor CORNFLOWERBLUE = ShapeFactory.INST.createColorInt( 89,221,255);
	public static final IColor MIDNIGHTBLUE   = ShapeFactory.INST.createColorInt(  0,112,145);
	public static final IColor NAVYBLUE       = ShapeFactory.INST.createColorInt( 15,117,255);
	public static final IColor ROYALBLUE      = ShapeFactory.INST.createColorInt(  0,127,255);
	public static final IColor BLUE           = ShapeFactory.INST.createColorInt(  0,  0,255);
	public static final IColor CERULEAN       = ShapeFactory.INST.createColorInt( 15,226,255);
	public static final IColor CYAN           = ShapeFactory.INST.createColorInt(  0,255,255);
	public static final IColor PROCESSBLUE    = ShapeFactory.INST.createColorInt( 10,255,255);
	public static final IColor SKYBLUE        = ShapeFactory.INST.createColorInt( 96,255,224);
	public static final IColor TURQUOISE      = ShapeFactory.INST.createColorInt( 38,255,204);
	public static final IColor TEALBLUE       = ShapeFactory.INST.createColorInt( 30,249,163);
	public static final IColor AQUAMARINE     = ShapeFactory.INST.createColorInt( 45,255,178);
	public static final IColor BLUEGREEN      = ShapeFactory.INST.createColorInt( 38,255,170);
	public static final IColor EMERALD        = ShapeFactory.INST.createColorInt(  0,255,127);
	public static final IColor JUNGLEGREEN    = ShapeFactory.INST.createColorInt(  2,255,122);
	public static final IColor SEAGREEN       = ShapeFactory.INST.createColorInt( 79,255,127);
	public static final IColor GREEN          = ShapeFactory.INST.createColorInt(  0,255,  0);
	public static final IColor FORESTGREEN    = ShapeFactory.INST.createColorInt(  0,224,  0);
	public static final IColor PINEGREEN      = ShapeFactory.INST.createColorInt(  0,191, 40);
	public static final IColor LIMEGREEN      = ShapeFactory.INST.createColorInt(127,255,  0);
	public static final IColor YELLOWGREEN    = ShapeFactory.INST.createColorInt(142,255, 66);
	public static final IColor SPRINGGREEN    = ShapeFactory.INST.createColorInt(188,255, 61);
	public static final IColor OLIVEGREEN     = ShapeFactory.INST.createColorInt(  0,153,  0);
	public static final IColor RAWSIENNA      = ShapeFactory.INST.createColorInt(140,  0,  0);
	public static final IColor SEPIA          = ShapeFactory.INST.createColorInt( 76,  0,  0);
	public static final IColor BROWN          = ShapeFactory.INST.createColorInt(102,  0,  0);
	public static final IColor TAN            = ShapeFactory.INST.createColorInt(219,147,112);
	public static final IColor GRAY           = ShapeFactory.INST.createColorInt(127,127,127);
	public static final IColor BLACK          = ShapeFactory.INST.createColor(  0,  0,  0);
	public static final IColor WHITE          = ShapeFactory.INST.createColorInt(255,255,255);
	public static final IColor PINK           = ShapeFactory.INST.createColorInt(255,192,203);
	public static final IColor DARKGRAY		 = ShapeFactory.INST.createColorInt(169,169,169);
	public static final IColor LIGHTGRAY		 = ShapeFactory.INST.createColorInt(211,211,211);
	public static final IColor OLIVE          = ShapeFactory.INST.createColor(0.5, 0.5, 0);


	public static final String N_LIME		    = "lime"; 
	public static final String N_TEAL		    = "teal"; 
	public static final String N_GREEN_YELLOW   = "GreenYellow"; 
	public static final String N_YELLOW         = "Yellow"; 
	public static final String N_GOLDEN_ROD     = "Goldenrod"; 
	public static final String N_DANDELION      = "Dandelion"; 
	public static final String N_APRICOT        = "Apricot"; 
	public static final String N_PEACH          = "Peach"; 
	public static final String N_MELON          = "Melon"; 
	public static final String N_YELLOW_ORANGE  = "YellowOrange"; 
	public static final String N_ORANGE         = "Orange"; 
	public static final String N_BURNT_ORANGE   = "BurntOrange"; 
	public static final String N_BITTERSWEET    = "Bittersweet"; 
	public static final String N_RED_ORANGE     = "RedOrange"; 
	public static final String N_MAHOGANY       = "Mahogany"; 
	public static final String N_MAROON         = "Maroon"; 
	public static final String N_BRICKRED       = "BrickRed"; 
	public static final String N_RED            = "Red"; 
	public static final String N_ORANGERED      = "OrangeRed"; 
	public static final String N_RUBINERED      = "RubineRed"; 
	public static final String N_WILDSTRAWBERRY = "WildStrawberry"; 
	public static final String N_SALMON         = "Salmon"; 
	public static final String N_CARNATIONPINK  = "CarnationPink"; 
	public static final String N_MAGENTA        = "Magenta"; 
	public static final String N_VIOLETRED      = "VioletRed"; 
	public static final String N_RHODAMINE      = "Rhodamine"; 
	public static final String N_MULBERRY       = "Mulberry"; 
	public static final String N_REDVIOLET      = "RedViolet"; 
	public static final String N_FUSHIA         = "Fuchsia"; 
	public static final String N_LAVENDER       = "Lavender"; 
	public static final String N_THISTLE        = "Thistle"; 
	public static final String N_ORCHID         = "Orchid"; 
	public static final String N_DARKORCHID     = "DarkOrchid"; 
	public static final String N_PURPLE         = "Purple"; 
	public static final String N_PLUM           = "Plum"; 
	public static final String N_VIOLET         = "Violet"; 
	public static final String N_ROYALPURPLE    = "RoyalPurple"; 
	public static final String N_BLUEVIOLET     = "BlueViolet"; 
	public static final String N_PERIWINKLE     = "Periwinkle"; 
	public static final String N_CADETBLUE      = "CadetBlue"; 
	public static final String N_CORNFLOWERBLUE = "CornflowerBlue"; 
	public static final String N_MIDNIGHTBLUE   = "MidnightBlue"; 
	public static final String N_NAVYBLUE       = "NavyBlue"; 
	public static final String N_ROYALBLUE      = "RoyalBlue"; 
	public static final String N_BLUE           = "Blue"; 
	public static final String N_CERULEAN       = "Cerulean"; 
	public static final String N_CYAN           = "Cyan"; 
	public static final String N_PROCESSBLUE    = "ProcessBlue"; 
	public static final String N_SKYBLUE        = "SkyBlue"; 
	public static final String N_TURQUOISE      = "Turquoise"; 
	public static final String N_TEALBLUE       = "TealBlue"; 
	public static final String N_AQUAMARINE     = "Aquamarine"; 
	public static final String N_BLUEGREEN      = "BlueGreen"; 
	public static final String N_EMERALD        = "Emerald"; 
	public static final String N_JUNGLEGREEN    = "JungleGreen"; 
	public static final String N_SEAGREEN       = "SeaGreen"; 
	public static final String N_GREEN          = "Green"; 
	public static final String N_FORESTGREEN    = "ForestGreen"; 
	public static final String N_PINEGREEN      = "PineGreen"; 
	public static final String N_LIMEGREEN      = "LimeGreen"; 
	public static final String N_YELLOWGREEN    = "YellowGreen"; 
	public static final String N_SPRINGGREEN    = "SpringGreen"; 
	public static final String N_OLIVEGREEN     = "OliveGreen"; 
	public static final String N_RAWSIENNA      = "RawSienna"; 
	public static final String N_SEPIA          = "Sepia"; 
	public static final String N_BROWN          = "Brown"; 
	public static final String N_TAN            = "Tan"; 
	public static final String N_GRAY           = "Gray"; 
	public static final String N_BLACK          = "Black"; 
	public static final String N_WHITE          = "White"; 

	public static final String N_CYAN_2         = "cyan"; 
	public static final String N_GRAY_2         = "gray"; 
	public static final String N_BLACK_2        = "black"; 
	public static final String N_WHITE_2        = "white"; 
	public static final String N_YELLOW_2       = "yellow"; 
	public static final String N_VIOLET_2       = "violet"; 
	public static final String N_BLUE_2         = "blue"; 
	public static final String N_PURPLE_2       = "purple"; 
	public static final String N_RED_2          = "red"; 
	public static final String N_ORANGE_2       = "orange"; 
	public static final String N_GREEN_2        = "green"; 
	public static final String N_MAGENTA_2      = "magenta"; 
	public static final String N_BROWN_2        = "brown"; 
	public static final String N_DARK_GRAY      = "darkgray"; 
	public static final String N_LIGHT_GRAY     = "lightgray"; 
	public static final String N_PINK     		= "pink"; 
	public static final String N_OLIVE			= "olive"; 

	public static final Colors INSTANCE 	= new Colors();

	private final Map<String, IColor> colourHT 		= new HashMap<>();

	private final Map<IColor, String> nameColourHT 	= new HashMap<>();

	/** The colours defined by the user and their name. */
	private final Map<String, IColor> userColourHT 		= new HashMap<>();

	/** The colours defined by the user and their name. */
	private final Map<IColor, String> userNameColourHT 	= new HashMap<>();

	/** The counter is used to name the user defined colours. */
	private int ctColours;


	private Colors() {
		super();
		createColourHashTable();
		createNameColourHashTable();
		ctColours = 0;
	}



	/**
	 * Creates the hashTable {@link #nameColourHT}.
	 * @since 1.9.2
	 */
	private void createColourHashTable() {
		colourHT.put(N_LIME, LIME);
		colourHT.put(N_TEAL, TEAL);
		colourHT.put(N_OLIVE, OLIVE);
		colourHT.put(N_CYAN_2, CYAN);
		colourHT.put(N_GRAY_2, GRAY);
		colourHT.put(N_BLACK_2, BLACK);
		colourHT.put(N_WHITE_2, WHITE);
		colourHT.put(N_YELLOW_2, YELLOW);
		colourHT.put(N_VIOLET_2, VIOLET);
		colourHT.put(N_BLUE_2, BLUE);
		colourHT.put(N_PURPLE_2, PURPLE);
		colourHT.put(N_RED_2, RED);
		colourHT.put(N_ORANGE_2, ORANGE);
		colourHT.put(N_GREEN_2, GREEN);
		colourHT.put(N_MAGENTA_2, MAGENTA);
		colourHT.put(N_BROWN_2, BROWN);
		colourHT.put(N_PINK, PINK);
		colourHT.put(N_GRAY, GRAY);
		colourHT.put(N_BLACK, BLACK);
		colourHT.put(N_WHITE, WHITE);
		colourHT.put(N_RED, RED);
		colourHT.put(N_GREEN, GREEN);
		colourHT.put(N_BLUE, BLUE);
		colourHT.put(N_VIOLET, VIOLET);
		colourHT.put(N_ORANGE, ORANGE);
		colourHT.put(N_PURPLE, PURPLE);
		colourHT.put(N_DARK_GRAY, DARKGRAY);
		colourHT.put(N_LIGHT_GRAY, LIGHTGRAY);
		colourHT.put(N_PINK, PINK);
		colourHT.put(N_GREEN_YELLOW, GREEN_YELLOW);
		colourHT.put(N_YELLOW, YELLOW);
		colourHT.put(N_GOLDEN_ROD, GOLDEN_ROD);
		colourHT.put(N_DANDELION, DANDELION);
		colourHT.put(N_APRICOT, APRICOT);
		colourHT.put(N_PEACH, PEACH);
		colourHT.put(N_MELON, MELON);
		colourHT.put(N_YELLOW_ORANGE, YELLOW_ORANGE);
		colourHT.put(N_BURNT_ORANGE, BURNT_ORANGE);
		colourHT.put(N_BITTERSWEET, BITTERSWEET);
		colourHT.put(N_RED_ORANGE, RED_ORANGE);
		colourHT.put(N_MAHOGANY, MAHOGANY);
		colourHT.put(N_MAROON, MAROON);
		colourHT.put(N_BRICKRED, BRICKRED);
		colourHT.put(N_ORANGERED, ORANGERED);
		colourHT.put(N_RUBINERED, RUBINERED);
		colourHT.put(N_WILDSTRAWBERRY, WILDSTRAWBERRY);
		colourHT.put(N_SALMON, SALMON);
		colourHT.put(N_CARNATIONPINK, CARNATIONPINK);
		colourHT.put(N_MAGENTA, MAGENTA);
		colourHT.put(N_VIOLETRED, VIOLETRED);
		colourHT.put(N_RHODAMINE, RHODAMINE);
		colourHT.put(N_MULBERRY, MULBERRY);
		colourHT.put(N_REDVIOLET, REDVIOLET);
		colourHT.put(N_FUSHIA, FUSHIA);
		colourHT.put(N_LAVENDER, LAVENDER);
		colourHT.put(N_THISTLE, THISTLE);
		colourHT.put(N_ORCHID, ORCHID);
		colourHT.put(N_DARKORCHID, DARKORCHID);
		colourHT.put(N_PLUM, PLUM);
		colourHT.put(N_ROYALPURPLE, ROYALPURPLE);
		colourHT.put(N_BLUEVIOLET, BLUEVIOLET);
		colourHT.put(N_PERIWINKLE, PERIWINKLE);
		colourHT.put(N_CADETBLUE, CADETBLUE);
		colourHT.put(N_CORNFLOWERBLUE, CORNFLOWERBLUE);
		colourHT.put(N_MIDNIGHTBLUE, MIDNIGHTBLUE);
		colourHT.put(N_NAVYBLUE, NAVYBLUE);
		colourHT.put(N_ROYALBLUE, ROYALBLUE);
		colourHT.put(N_CERULEAN, CERULEAN);
		colourHT.put(N_CYAN, CYAN);
		colourHT.put(N_PROCESSBLUE, PROCESSBLUE);
		colourHT.put(N_SKYBLUE, SKYBLUE);
		colourHT.put(N_TURQUOISE, TURQUOISE);
		colourHT.put(N_TEALBLUE, TEALBLUE);
		colourHT.put(N_AQUAMARINE, AQUAMARINE);
		colourHT.put(N_BLUEGREEN, BLUEGREEN);
		colourHT.put(N_EMERALD, EMERALD);
		colourHT.put(N_JUNGLEGREEN, JUNGLEGREEN);
		colourHT.put(N_SEAGREEN, SEAGREEN);
		colourHT.put(N_FORESTGREEN, FORESTGREEN);
		colourHT.put(N_PINEGREEN, PINEGREEN);
		colourHT.put(N_LIMEGREEN, LIMEGREEN);
		colourHT.put(N_YELLOWGREEN, YELLOWGREEN);
		colourHT.put(N_SPRINGGREEN, SPRINGGREEN);
		colourHT.put(N_OLIVEGREEN, OLIVEGREEN);
		colourHT.put(N_RAWSIENNA, RAWSIENNA);
		colourHT.put(N_SEPIA, SEPIA);
		colourHT.put(N_BROWN, BROWN);
		colourHT.put(N_TAN, TAN);
	}



	/**
	 * Creates the hashTable {@link #colourHT}.
	 * @since 1.9.2
	 */
	private void createNameColourHashTable() {
		nameColourHT.put(LIME, N_LIME);
		nameColourHT.put(TEAL, N_TEAL);
		nameColourHT.put(OLIVE, N_OLIVE);
		nameColourHT.put(GRAY, N_GRAY_2);
		nameColourHT.put(BLACK, N_BLACK_2);
		nameColourHT.put(WHITE, N_WHITE_2);
		nameColourHT.put(RED, N_RED_2);
		nameColourHT.put(GREEN, N_GREEN_2);
		nameColourHT.put(BLUE, N_BLUE_2);
		nameColourHT.put(VIOLET, N_VIOLET_2);
		nameColourHT.put(ORANGE, N_ORANGE_2);
		nameColourHT.put(PURPLE, N_PURPLE_2);
		nameColourHT.put(DARKGRAY, N_DARK_GRAY);
		nameColourHT.put(LIGHTGRAY, N_LIGHT_GRAY);
		nameColourHT.put(PINK, N_PINK);
		nameColourHT.put(GREEN_YELLOW, N_GREEN_YELLOW);
		nameColourHT.put(YELLOW, N_YELLOW_2);
		nameColourHT.put(GOLDEN_ROD, N_GOLDEN_ROD);
		nameColourHT.put(DANDELION, N_DANDELION);
		nameColourHT.put(APRICOT, N_APRICOT);
		nameColourHT.put(PEACH, N_PEACH);
		nameColourHT.put(MELON, N_MELON);
		nameColourHT.put(YELLOW_ORANGE, N_YELLOW_ORANGE);
		nameColourHT.put(BURNT_ORANGE, N_BURNT_ORANGE);
		nameColourHT.put(BITTERSWEET, N_BITTERSWEET);
		nameColourHT.put(RED_ORANGE, N_RED_ORANGE);
		nameColourHT.put(MAHOGANY, N_MAHOGANY);
		nameColourHT.put(MAROON, N_MAROON);
		nameColourHT.put(BRICKRED, N_BRICKRED);
		nameColourHT.put(ORANGERED, N_ORANGERED);
		nameColourHT.put(RUBINERED, N_RUBINERED);
		nameColourHT.put(WILDSTRAWBERRY, N_WILDSTRAWBERRY);
		nameColourHT.put(SALMON, N_SALMON);
		nameColourHT.put(CARNATIONPINK, N_CARNATIONPINK);
		nameColourHT.put(MAGENTA, N_MAGENTA_2);
		nameColourHT.put(VIOLETRED, N_VIOLETRED);
		nameColourHT.put(RHODAMINE, N_RHODAMINE);
		nameColourHT.put(MULBERRY, N_MULBERRY);
		nameColourHT.put(REDVIOLET, N_REDVIOLET);
		nameColourHT.put(FUSHIA, N_FUSHIA);
		nameColourHT.put(LAVENDER, N_LAVENDER);
		nameColourHT.put(THISTLE, N_THISTLE);
		nameColourHT.put(ORCHID, N_ORCHID);
		nameColourHT.put(DARKORCHID, N_DARKORCHID);
		nameColourHT.put(PLUM, N_PLUM);
		nameColourHT.put(ROYALPURPLE, N_ROYALPURPLE);
		nameColourHT.put(BLUEVIOLET, N_BLUEVIOLET);
		nameColourHT.put(PERIWINKLE, N_PERIWINKLE);
		nameColourHT.put(CADETBLUE, N_CADETBLUE);
		nameColourHT.put(CORNFLOWERBLUE ,N_CORNFLOWERBLUE);
		nameColourHT.put(MIDNIGHTBLUE, N_MIDNIGHTBLUE);
		nameColourHT.put(NAVYBLUE, N_NAVYBLUE);
		nameColourHT.put(ROYALBLUE, N_ROYALBLUE);
		nameColourHT.put(CERULEAN, N_CERULEAN);
		nameColourHT.put(CYAN, N_CYAN_2);
		nameColourHT.put(PROCESSBLUE, N_PROCESSBLUE);
		nameColourHT.put(SKYBLUE, N_SKYBLUE);
		nameColourHT.put(TURQUOISE, N_TURQUOISE);
		nameColourHT.put(TEALBLUE, N_TEALBLUE);
		nameColourHT.put(AQUAMARINE, N_AQUAMARINE);
		nameColourHT.put(BLUEGREEN, N_BLUEGREEN);
		nameColourHT.put(EMERALD, N_EMERALD);
		nameColourHT.put(JUNGLEGREEN, N_JUNGLEGREEN);
		nameColourHT.put(SEAGREEN, N_SEAGREEN);
		nameColourHT.put(FORESTGREEN, N_FORESTGREEN);
		nameColourHT.put(PINEGREEN, N_PINEGREEN);
		nameColourHT.put(LIMEGREEN, N_LIMEGREEN);
		nameColourHT.put(YELLOWGREEN, N_YELLOWGREEN);
		nameColourHT.put(SPRINGGREEN, N_SPRINGGREEN);
		nameColourHT.put(OLIVEGREEN, N_OLIVEGREEN);
		nameColourHT.put(RAWSIENNA, N_RAWSIENNA);
		nameColourHT.put(SEPIA, N_SEPIA);
		nameColourHT.put(BROWN, N_BROWN_2);
		nameColourHT.put(TAN, N_TAN);
	}


	/**
	 * @param name The name of the searched colour.
	 * @return The corresponding predefined colour or null.
	 * *
	 */
	public Optional<IColor> getPredefinedColour(final String name) {
		if(name==null) return Optional.empty();
		return Optional.ofNullable(colourHT.get(name));
	}


	/**
	 * Allows to get the name of  a given colour.
	 * @param colour The colour that we want the name .
	 * @return The name of the colour : null if it can not be found.
	 */
	public Optional<String> getColourName(final IColor colour) {
		if(colour==null) return Optional.empty();
		String name = nameColourHT.get(colour);
		if(name==null) name = userNameColourHT.get(colour);
		return Optional.ofNullable(name);
	}


	/**
	 * Allows to get a colour with its name.
	 * @param name The name of the wanted colour.
	 * @return The colour, null if the name is invalid of do not correspond at any colour.
	 */
	public Optional<IColor> getColour(final String name) {
		if(name==null) return Optional.empty();
		IColor c = userColourHT.get(name);
		if(c==null) c = colourHT.get(name);
		return Optional.ofNullable(c);
	}


	/**
	 * Adds a colour defined by the user.
	 * @param colour The colour to add.
	 * @return The name of this colour.
	 */
	public Optional<String> addUserColour(final IColor colour) {
		if(colour==null) return Optional.empty();
		final String name = generateColourName();
		addUserColour(colour, name);
		return Optional.of(name);
	}


	/**
	 * Adds a colour defined by the user.
	 * @param colour The colour to add.
	 * @param name The name of the colour.
	 */
	public void addUserColour(final IColor colour, final String name) {
		if(colour!=null && name!=null && !name.isEmpty()) {
			userColourHT.put(name, colour);
			userNameColourHT.put(colour, name);
		}
	}


	/**
	 * @return A unique name for a user defined colour.
	 * *
	 */
	private String generateColourName() {
		return "colour" + ctColours++; 
	}


	/**
	 * Allows to get the PSTricks code of a given colour.
	 * @param colourName The name of the colour used to generate the code.
	 * @return The code of the colour or an empty string if the given colour is not valid.
	 */
	public String getUsercolourCode(final String colourName) {
		final Optional<IColor> colour = getColour(colourName);

		if(colour.isPresent()) {
			final IColor col = colour.get();
			return "\\definecolor{" + colourName + "}{rgb}{" +  //$NON-NLS-2$
				(float)(col.getR()/255.) + ',' + (float)(col.getG()/255.) + ',' + (float)(col.getB()/255.) + "}\n";
		}
		return "";
	}


	/**
	 * Converts an HTML (i.e. hexa) colour to an rgb one.
	 * @param hexaCode The hexadecimal code of the colour.
	 * @return The corresponding rgb colour.
	 * @throws IllegalArgumentException If the given argument is not valid (null or its length lesser than 8 characters).
	 * *
	 */
	public IColor convertHTML2rgb(final String hexaCode) {
		if(hexaCode==null || hexaCode.length()<7) throw new IllegalArgumentException(hexaCode);
		 return ShapeFactory.INST.createColor(Integer.valueOf(hexaCode.substring(1, 3), 16)/255.0,
		            Integer.valueOf(hexaCode.substring(3, 5), 16)/255.0,
		            Integer.valueOf(hexaCode.substring(5), 16)/255.0);
	}


	/**
	 * Converts an RGB [0-255] colour to an rgb [0-1] one.
	 * @param r The red level between 0 and 255.
	 * @param g The green level between 0 and 255.
	 * @param b The blue level between 0 and 255.
	 * @return The corresponding rgb colour.
	 * @throws IllegalArgumentException If one of the given arguments is not valid.
	 * *
	 */
	public IColor convertRGB2rgb(final double r, final double g, final double b) {
		if(r<0 || g<0 || b<0) throw new IllegalArgumentException(r + " "  + g + " " + b);  //$NON-NLS-2$
		return ShapeFactory.INST.createColor(r/255.0, g/255.0, b/255.0);
	}


	/**
	 * Converts a CMYK colour to an rgb one.
	 * @param c The c level between 0 and 1.
	 * @param m The m level between 0 and 1.
	 * @param y The y level between 0 and 1.
	 * @param k The k level between 0 and 1.
	 * @return The corresponding rgb colour.
	 * @since 2.0.0
	 */
	public IColor convertcmyk2rgb(final double c, final double m, final double y, final double k) {
		if(c < 0 || c > 1) throw new IllegalArgumentException(String.valueOf(c));
		if(m < 0 || m > 1) throw new IllegalArgumentException(String.valueOf(m));
		if(y < 0 || y > 1) throw new IllegalArgumentException(String.valueOf(y));
		if(k < 0 || k > 1) throw new IllegalArgumentException(String.valueOf(k));
		return ShapeFactory.INST.createColor(1.0-(c*(1.0-k)+k), 1.0-(m*(1.0-k)+k), 1.0-(y*(1.0-k)+k));
	}


	/**
	 * Converts a gray colour in an rgb one.
	 * @param g The gray level between 0 an 1.
	 * @return The corresponding rgb colour.
	 * @since 2.0.0
	 */
    public IColor convertgray2rgb(final double g) {
		if(g < 0 || g > 1) throw new IllegalArgumentException(String.valueOf(g));
		return ShapeFactory.INST.createColor(g, g, g);
	}
}
