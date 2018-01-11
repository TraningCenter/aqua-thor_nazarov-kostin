package view.implementation;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import dto.FishDto;
import dto.OceanDto;
import model.fish.implementation.FishType;
import model.fish.interfaces.Fish;
import model.parameters.FishState;
import model.parameters.Flow;
import model.parameters.Rectangle;
import model.parameters.Vector;
import view.interfaces.OceanVisualizer;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefaultOceanVisualizer implements OceanVisualizer {

    class FishIdentity{
        FishType fishType;
        FishState fishState;

        public FishIdentity(FishDto fish) {
            this.fishType=fish.getFishType();
            this.fishState=fish.getFishState();
        }

        public FishIdentity(FishType fishType, FishState fishState) {
            this.fishType = fishType;
            this.fishState = fishState;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FishIdentity that = (FishIdentity) o;
            return fishType == that.fishType &&
                    fishState == that.fishState;
        }

        @Override
        public int hashCode() {

            return Objects.hash(fishType, fishState);
        }
    }

    private final TextColor.ANSI WATER_COLOR = TextColor.ANSI.BLUE;
    private final TextColor.ANSI FLOW_COLOR = TextColor.ANSI.WHITE;
    private final TextColor.ANSI FISH_COLOR = TextColor.ANSI.GREEN;
    private final TextColor.ANSI SHARK_COLOR = TextColor.ANSI.RED;

    private final TextCharacter WATER_CHAR = new TextCharacter(' ', WATER_COLOR, WATER_COLOR, SGR.BOLD);

    private final Map<FishIdentity, TextCharacter> FISH_CHARS = new HashMap<FishIdentity, TextCharacter>(){{
        put(new FishIdentity(FishType.PASSIVE,FishState.BIRTH), new TextCharacter('.', FISH_COLOR, WATER_COLOR, SGR.BOLD));
        put(new FishIdentity(FishType.PASSIVE,FishState.MOVING), new TextCharacter('@', FISH_COLOR, WATER_COLOR, SGR.BOLD));
        put(new FishIdentity(FishType.PASSIVE,FishState.EATING), new TextCharacter('e', FISH_COLOR, WATER_COLOR, SGR.BOLD));
        put(new FishIdentity(FishType.AGGRESSIVE,FishState.BIRTH), new TextCharacter('.', SHARK_COLOR, WATER_COLOR, SGR.BOLD));
        put(new FishIdentity(FishType.AGGRESSIVE,FishState.MOVING), new TextCharacter('@', SHARK_COLOR, WATER_COLOR, SGR.BOLD));
        put(new FishIdentity(FishType.AGGRESSIVE,FishState.EATING), new TextCharacter('e', SHARK_COLOR, WATER_COLOR, SGR.BOLD));
    }};

    private final Map<Vector, TextCharacter> DIRECTION_CHARS = new HashMap<Vector, TextCharacter>(){{
        put(new Vector(0,-1), new TextCharacter('↑', FLOW_COLOR, WATER_COLOR));
        put(new Vector(-1,0), new TextCharacter('←', FLOW_COLOR, WATER_COLOR));
        put(new Vector(0,1), new TextCharacter('↓', FLOW_COLOR, WATER_COLOR));
        put(new Vector(1,0), new TextCharacter('→', FLOW_COLOR, WATER_COLOR));
    }};


    private final TextGraphics textGraphics;
    private final TerminalScreen screen;

    public DefaultOceanVisualizer() throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setTerminalEmulatorFontConfiguration(new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.EVERYTHING, new Font("Courier New", Font.BOLD,  30)));
        screen = defaultTerminalFactory.createScreen();
        screen.startScreen();
        screen.setCursorPosition(null);
        textGraphics = screen.newTextGraphics();

    }

    @Override
    public void visualize(OceanDto oceanDto) throws IOException {
        Vector oceanSize = oceanDto.getOceanSize();

        fillWater(oceanSize);
        drawFlows(oceanDto.getFlows());
        drawFishes(oceanDto.getFishes());

        screen.refresh();
    }

    private void drawFishes(List<FishDto> fishes) {
        fishes.forEach(fish -> textGraphics.setCharacter(createTerminalPosition(fish.getPosition()),
                FISH_CHARS.get(new FishIdentity(fish))));
    }

    private void drawFlows(List<Flow> flows) {
        flows.forEach(flow -> textGraphics.fillRectangle(createTerminalPosition(flow.getRectangle()),
                createTerminalSize(flow.getRectangle()), DIRECTION_CHARS.get(flow.getDirection())));
    }

    private void drawFish(FishDto fish){

    }

    private void fillWater(Vector oceanSize) {
        textGraphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, new TerminalSize(oceanSize.getX(), oceanSize.getY()), WATER_CHAR);
    }

    private TerminalPosition createTerminalPosition(Vector vector){
        return new TerminalPosition(vector.getX(),vector.getY());
    }

    private TerminalPosition createTerminalPosition(Rectangle rectangle){
        return new TerminalPosition(rectangle.getX(),rectangle.getY());
    }

    private TerminalSize createTerminalSize(Rectangle rectangle){
        return new TerminalSize(rectangle.getWidth(),rectangle.getHeight());
    }
}
