/*
 * This file is part of the IxiaS services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package net.ixias
package core.port.adapter.persistence.backend

import scala.util.Try

/** Backend for the basic database and session handling features. */
trait Backend extends DataSource {

  /** The type of database objects used by this backend. */
  type Database >: Null <: AnyRef

  /** Get a Database instance from connection pool. */
  def getDatabase(dsn: String)(implicit ctx: Context): Try[Database]
}
