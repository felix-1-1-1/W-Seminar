import greenfoot.*;
public class DevMode extends World {
    FlugObjekt flugObjekt;

    public DevMode() {
        super(1500, 700, 1);

        flugObjekt =  new FlugObjekt("blue-draught.png");
        prepareDev();

    }

    void run()
    {
        flugObjekt.run();
    }

    private void prepareDev() {
        int baseX = 1350;
        int baseY = 50;
        int distance = 20;

        addObject(flugObjekt, baseX, baseY);

        StartButtonDev StartButtonDev = new StartButtonDev();
        addObject(StartButtonDev, baseX, baseY);

        Toggle Toggle = new Toggle();
        addObject(Toggle, baseX + 100, baseY - 20);

        NegSwitch NegSwitch = new NegSwitch();
        addObject(NegSwitch, 1460, 435);

        DeleteTrace DeleteTrace = new DeleteTrace();
        addObject(DeleteTrace, 1450, 80);

        BackButton BackButton = new BackButton();
        addObject(BackButton,135,646);

        for (Object obj : this.getObjects(FlugObjekt.class)) {
            FlugObjekt FJ = (FlugObjekt) obj;
            FJ.setSliders();
            FJ.prepareText();
        }
    }

}
