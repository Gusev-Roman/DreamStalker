package com.legacy.goodnightsleep.client.gui;

//import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;

/** 
 * GuiDownloadTerrain - замощенный землей экран перехода между измерениями, скрывающий прорисовку мира
 */
public class GuiLoadingDreams extends GuiDownloadTerrain    //GuiScreen
{	
    public static final ResourceLocation DREAM_BACKGROUND = new ResourceLocation("goodnightsleep", "textures/blocks/natural/dream_dirt.png");

	public boolean nightmare;
	
	 public GuiLoadingDreams(boolean dimension)
	{
		 nightmare = dimension;
	}

	public void initGui()
	 {
		 this.buttonList.clear();
	 }
	 
    /**
     * Авторы GNS отказались от использования GUI в новых версиях.
     */
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (nightmare)
        {
        	this.mc.getTextureManager().bindTexture(DREAM_BACKGROUND);  //OPTIONS_BACKGROUND
        	this.drawCenteredString(this.fontRenderer, "You dream of horrible things...", this.width / 2, this.height / 2 - 50, 0xFFFFCF);  // yellow letters 
        }
        else
        {
        	this.mc.getTextureManager().bindTexture(DREAM_BACKGROUND);
        	this.drawCenteredString(this.fontRenderer, "You dream of peaceful lands...", this.width / 2, this.height / 2 - 50, 16777215);
        }
        this.drawBackground(0);
        this.drawCenteredString(this.fontRenderer, I18n.format("multiplayer.downloadingTerrain"), this.width / 2, this.height / 2 - 50, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
