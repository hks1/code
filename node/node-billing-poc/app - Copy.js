let connection;
var oracledb = require('oracledb');
const sendMsg = require('./producer')
require('dotenv').config();

//const producer = Kafka.producer()

(async function() {
try{
   connection = await oracledb.getConnection({
        user : process.env.ORACLE_USER,
        password : process.env.ORACLE_PASSWORD,
        //connectString : '10.10.30.226/pindbp'
        connectString : 'j2p1cbrmdb1.iaglab.com/cbrm12c'
   }, (err, conn) => {
       if(err){
           console.log(err);
       } else {
        console.log("Successfully connected to Oracle!");
        //console.log(process.env.ORACLE_USER)
        //console.log(process.env.ORACLE_PASSWORD)
        //conn.execute("select account_no, action, reason_code from hns_lut_message_t where status = 'P'", (err, result) => {
          conn.execute("select account_no, action, reason_code from hns_lut_message_t where status = :status", {status: 'P'}, (err, result) => {
            if(err){
                console.log(err);
                doRelease(conn);
                return;
            } else {
                //console.log(result);
                //console.log(JSON.stringify(result.rows));
                doRelease(conn);
                result.rows.forEach(element => {
                  //console.log(element)
                  //console.log(JSON.stringify(element))
                  let collMsg = {}
                  collMsg.SAN = element[0]
                  collMsg.ACTION = element[1]
                  collMsg.REASON_CODE = element[2]
                  console.log(collMsg)

                  //send message to Kafka
                  sendMsg(JSON.stringify(collMsg), collMsg.SAN).catch(console.error)
                });
                
            }
        } )
       }
   });
   
//    connection.execute("Select * " +
//                         "from account_t where rownum < 2;", 
//                         function(err, result) {
//        if(err){
//            console.log('error in executing sql.')
//            console.error(err.message);
//            doRelease(connection);
//            return;
//        }
//        console.log("--results--");
//        console.log(result);
//        console.log(result.rows);
//        doRelease(connection);
//    } )
} catch(err) {
    console.log("Error: ", err);
  } finally {
    if (connection) {
      try {
        await connection.close();
      } catch(err) {
        console.log("Error when closing the database connection: ", err);
      }
    }
  }
})()

function doRelease(connection)
{
	connection.close(function(err){
		if(err)
		{
			console.error("close db connection failed:" + err.message);
		}

		return;
	});
}