const oracledb = require('oracledb');
require('dotenv').config

oracledb.getConnection(
  {
    user          : process.env.ORACLE_USER,
    password      : process.env.ORACLE_PASSWORD,
    connectString : "10.10.30.226/pindbp"
  },
  function(err, connection)
  {
    if (err) {
      console.error(err.message);
      return;
    }
    connection.execute(
      "SELECT * " +
        "FROM account_t where rownum < 2",
      function(err, result)
      {
        if (err) {
          console.error(err.message);
          doRelease(connection);
          return;
        }
        console.log(result.rows);
        doRelease(connection);
      });
  });



module.exports = {
    oracledb
};