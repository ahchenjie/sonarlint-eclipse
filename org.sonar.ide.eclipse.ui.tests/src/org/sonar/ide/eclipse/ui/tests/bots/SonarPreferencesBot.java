/*
 * Sonar Eclipse
 * Copyright (C) 2010-2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.ide.eclipse.ui.tests.bots;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class SonarPreferencesBot {
  private final SWTBot bot = new SWTBot();

  public SonarPreferencesBot() {
    bot.menu("Window").menu("Preferences").click();
    bot.shell("Preferences").activate();
    bot.tree().select("Sonar");
  }

  public int getServersCount() {
    return bot.table().rowCount();
  }

  public SonarPreferencesBot select(String url) {
    bot.table().getTableItem(url).select();
    return this;
  }

  public SonarServerWizardBot add() {
    bot.button("Add...").click();
    bot.waitUntil(Conditions.shellIsActive("Add Sonar Server"));
    return new SonarServerWizardBot(false);
  }

  public SonarServerWizardBot edit() {
    bot.button("Edit...").click();
    bot.waitUntil(Conditions.shellIsActive("Edit Sonar Server"));
    return new SonarServerWizardBot(true);
  }

  public SonarPreferencesBot remove() {
    bot.button("Remove").click();
    bot.waitUntil(Conditions.shellIsActive("Remove sonar server connection"));
    bot.button("OK").click();
    return this;
  }

  public void ok() {
    SWTBotShell shell = bot.shell("Preferences").activate();
    bot.button("OK").click();
    bot.waitUntil(Conditions.shellCloses(shell));
  }
}
