package com.sl.pojo;

import java.util.List;

public class Menu {
	private Function mainMenu;
	private List<Function> subMenu;
	public Menu() {
		super();
	}
	public Menu(Function mainMenu, List<Function> subMenu) {
		super();
		this.mainMenu = mainMenu;
		this.subMenu = subMenu;
	}
	public Function getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(Function mainMenu) {
		this.mainMenu = mainMenu;
	}
	public List<Function> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Function> subMenu) {
		this.subMenu = subMenu;
	}
	
}
