let connection;
var oracledb = require('oracledb');
const sendMsg = require('./producer')
require('dotenv').config();

setInterval(async function() {
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
        //conn.execute("select account_no, action, reason_code from hns_lut_message_t where status = 'P'", (err, result) => {
          conn.execute("select account_no, action, reason_code from hns_lut_message_t where status = :status", {status: 'P'}, (err, result) => {
            if(err){
                console.log(err);
                doRelease(conn);
                return;
            } else {
                doRelease(conn);
                result.rows.forEach(element => {
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
}, 1000)

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