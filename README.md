# SaveSinaStockData
Use Sina API to save stock history data by Java language
# Guide
<p>Before run this application, you shoule create a database named sinastockdata(or you like) in MySQL(or others).You can execute sinastockdata.sql ,all database structure in this file.</p>
<p> In src\ReadExcelAndSaveStockCode.java, you will find </p><code>public static final String URL="jdbc:mysql://localhost:3306/sinastockdata?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";</code> <p>Change it for your local enviroment.</p>

<hr>
<p>ReadExcelAndSaveStockCode.java : read stock code from excel file and save into database.You should run this firstly.</p>
<p>SaveStockDataFromSina.java :  Use Sina API to save stock history data. You should run this secondly.</p>
<p>QueryFail.java :  Use Sina API to save stock history data which failed in SaveStockDataFromSina. You should run this lastly.</p>
#License

<p>Copyright yilihjy</p>

<p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>

   <p>http://www.apache.org/licenses/LICENSE-2.0</p>
<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
