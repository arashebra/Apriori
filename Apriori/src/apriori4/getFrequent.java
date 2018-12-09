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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author DP
 */
public class getFrequent {

    private File settingFile;
    private File transactionFile;
    double minSup;
    private ArrayList<HashMap<String,Integer>> frequent = new ArrayList<>();
    
    public getFrequent(File settingFile, File transactionFile , double minSup) throws IOException {
        this.settingFile = settingFile;
        this.transactionFile = transactionFile;
        this.minSup = minSup;
        readSettingFile(settingFile);
    }
    

    private void readSettingFile(File file) throws IOException
    {
        int col = 0;
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        for(String line; (line = br1.readLine()) != null; ) {
            String[] setting = line.split("\\s+");
            col = setting.length;
        }
        
        String[] setting = new String[col];
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        for(String line; (line = br.readLine()) != null; ) {
            setting = line.split("\\s+");
            for (int i = 0; i < setting.length; i++) {
                HashMap<String,Integer> hm = new HashMap<>();
                hm.put(setting[i], 0);
                frequent.add(hm);
            }
        }

        makeSubset(setting);

        //Integer[] cols = {0, 2, 3, 4};
        //readTransanctionFile(transactionFile,setting,cols);
    }
    

    private void makeSubset(String[] set) throws IOException
    {
        List<Integer> temp = new ArrayList<Integer>();
        
        int[] cols = new int[set.length];
        
        int n = set.length; 

        for (int i = 1; i < (1<<n); i++) 
        { 
            int j;
            for (j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0){ 
                    temp.add(j);
                }
            }
            temp.add(-1);
        }
        

        int i = 0;
        int j = 0;
        for (; i < temp.size(); i++) 
        {
            if (temp.get(i) == -1) 
            {
                List l = temp.subList(j, i);
                if (l.contains(-1)) {
                    l.remove(0);
                }
                
                Integer[] arr = new Integer[l.size()]; 
                arr = (Integer[]) l.toArray(arr); 

                readTransanctionFile(transactionFile,set,arr);

                for (; j < i; j++) 
                {
                    
                }
                j = i;
                
            }
           
        }

    }
    
    
    private void readTransanctionFile(File file, String[] setting, Integer[] cols) throws IOException
    {
        int frequency = 0;
        int lineCount = 0;
            
        BufferedReader br = new BufferedReader(new FileReader(file));
        for(String line; (line = br.readLine()) != null; ) {
            int temp = 0;
            String[] transaction = line.split("\\s+");
            for (int i = 0; i < cols.length; i++) {
                if ( Integer.parseInt(transaction[cols[i]])==1 ) {
                    ++temp;
                }
            }
            if (cols.length == temp) {

                frequency++;
            }
            lineCount++;
        }

        if ( ((double)frequency/lineCount) > minSup) {
            for (Integer col : cols) {
                    System.out.print(setting[col]+",");
                }
            System.out.println("\n"+(double)frequency/lineCount+"\n\n");
        }
    }
    
}
