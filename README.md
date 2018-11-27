# SaveSinaStockData
<p>注意：这个项目并没有在维护</p>
<p>封装新浪股票历史查询和实时查询的API，同时提供将历史数据或实时数据保存进入mysql数据库的方法</p>
<p>注意：依赖新浪这两个API：<br>示例：http://hq.sinajs.cn/list=sz000002<br>示例：http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz000002&scale=5&datalen=10<br>如果这两个API无法使用，那么无法使用本项目</p>
<p>项目使用gradle构建，如果你需要的话，旧版本的项目在old文件夹内</p>

# Install
<p>项目已上传maven仓库，可以使用maven或gradle</p>
<p>gradle：<br>在build.gradle中添加依赖<br></p>
<code>compile group: 'com.yilihjy', name: 'SaveSinaStockData', version: '1.0.1'</code>
<p>maven：<br>在pom.xml中添加依赖</p>
```xml
<dependency>
    <groupId>com.yilihjy</groupId>
    <artifactId>SaveSinaStockData</artifactId>
    <version>1.0.1</version>
</dependency>
```
# Guide
<p>在demo文件夹内有示例demo</p>
<p>HistoryData类提供调用历史数据API的方法</p>
<p>RealTimeData类提供调用实时数据API的方法</p>
<p>SaveData类提供了一些保存数据进入mysql数据库的方法</p>

# License

<p>Copyright 2016-2017 <a href="https://yilihjy.com">yilihjy</a></p>

<p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at</p>

   <p><a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a></p>
<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
