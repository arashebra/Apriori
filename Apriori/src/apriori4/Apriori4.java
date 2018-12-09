/*
 * Copyright 2018 Arash Ebrahimnezhad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package apriori4;

import com.sun.glass.ui.SystemClipboard;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author DP
 */
public class Apriori4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner minSup = new Scanner(System.in);
        System.err.print("Please Enter MinSup (double): ");
        new getFrequent(new File("config.txt"), new File("transaction.txt"),minSup.nextDouble());
    }
    
}
