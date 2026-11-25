/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rage;

import java.awt.BasicStroke;
import rage.roots.Basic;

/**
 *
 * @author rash4
 */
public class Rage {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Test.write();
        Test.read();
        Basic.initializeRootDir();
        System.out.println(Basic.DATA);
    }
}
