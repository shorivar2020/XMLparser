<h1>Parsing XML FILE and add it in DB</h1>
<p>Gets parameter when executing from commandline
(as input parameter)</p>
<p>
The parameter contains path to configuration 
text file - config.ini with <b>DatabaseInsertData</b></p>
<p>Application connect and download a 
response with <b>DownloadFileFromUrl</b></p>
<p>
Application takes this received XML data, 
parses through the data (Using method SAX) and saves the data
to a DB table with <b>SAX</b></p>
<p><b>Handler</b> do reaction from SAX parser on XML tags</p>
<p>All work with SQL in <b>Action</b></p>
<p>My DS is MYSQL local hostname and port 3306</p>
<p>For control version of DATA use <b>SCD2</b>
(Timestamp and 1 - actual version, 0 - old)</p>
<p>Structure of DB is Products with another tables 
depends on it by ID</p>
<p>For Cover cases do another Undefined data table
where write XML tag + value</p>
<p>Log of application in logFile.log with rulers
in <b>resources/logback.xml</b></p>
