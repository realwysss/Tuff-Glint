package dev.tfgl;

import java.util.Arrays;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CfgScr {
	static Component t(String s) {
		return Component.literal(s);
	}

	public static Screen mk(Screen p) {
		ConfigBuilder b = ConfigBuilder.create().setParentScreen(p).setTitle(t("Tuff Glint")).setSavingRunnable(Cfg::sv);
		ConfigCategory cc = b.getOrCreateCategory(t("Settings"));
		ConfigEntryBuilder e = b.entryBuilder();
		Cfg c = Cfg.c;
		cc.addEntry(e.startBooleanToggle(t("Tuff Glint On"), c.on).setDefaultValue(true).setTooltip(t("Turn this off to go back to the normal glint.")).setSaveConsumer(v -> c.on = v).build());
		String[] ps = {"Normal", "Aqua Pack", "Hello Kitty Pack", "Cry4me Pack", "My Own Image"};
		cc.addEntry(e.startSelector(t("Glint Look"), ps, ps[c.pre]).setDefaultValue(ps[0]).setTooltip(t("Normal uses the glint of the game or your resource pack. The pack ones are the glints from the HitsOnly packs. My Own Image uses item.png and armor.png from the config/tuffglint folder.")).setSaveConsumer(v -> c.pre = Arrays.asList(ps).indexOf(v)).build());
		cc.addEntry(e.startIntSlider(t("Strength"), c.str, 0, 1000).setDefaultValue(100).setTextGetter(v -> t(v + "%")).setTooltip(t("How bright the glint is. 100% is the normal game. 1000% is 10 times stronger.")).setSaveConsumer(v -> c.str = v).build());
		cc.addEntry(e.startIntSlider(t("Speed"), c.spd, 10, 1000).setDefaultValue(100).setTextGetter(v -> t(v + "%")).setTooltip(t("How fast the glint moves. This goes on top of the glint speed in the video settings.")).setSaveConsumer(v -> c.spd = v).build());
		cc.addEntry(e.startIntSlider(t("Size"), c.siz, 25, 400).setDefaultValue(100).setTextGetter(v -> t(v + "%")).setTooltip(t("How big the glint pattern is. Bigger shows fewer, larger shapes.")).setSaveConsumer(v -> c.siz = v).build());
		cc.addEntry(e.startIntSlider(t("Angle"), c.ang, -180, 180).setDefaultValue(10).setTextGetter(v -> t(v + " degrees")).setTooltip(t("The direction the glint moves in.")).setSaveConsumer(v -> c.ang = v).build());
		cc.addEntry(e.startColorField(t("Tint"), c.col).setDefaultValue(0xffffff).setTooltip(t("Recolors the glint. White keeps the colors of the glint image.")).setSaveConsumer(v -> c.col = v & 0xffffff).build());
		cc.addEntry(e.startBooleanToggle(t("Items"), c.itm).setDefaultValue(true).setTooltip(t("Show the glint on items.")).setSaveConsumer(v -> c.itm = v).build());
		cc.addEntry(e.startBooleanToggle(t("Armor"), c.arm).setDefaultValue(true).setTooltip(t("Show the glint on armor.")).setSaveConsumer(v -> c.arm = v).build());
		return b.build();
	}
}
