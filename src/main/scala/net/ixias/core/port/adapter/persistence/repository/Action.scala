/*
 * This file is part of the IxiaS services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package net.ixias
package core.port.adapter.persistence.repository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import core.port.adapter.persistence.model.{ DataSourceName, Converter }
import core.port.adapter.persistence.backend.BasicBackend
import core.port.adapter.persistence.io.{ IOActionContext, EntityIOActionContext }

/** A action request. */
trait ActionRequest[T <: BasicBackend] {
  val backend: T
  val dsn:     DataSourceName
}

/** A builder for generic Actions that generalizes over the type of requests. */
trait ActionFunction[-R, +P] {

  /** Invoke the block.
    * This is the main method that an ActionBuilder has to implement */
  def invokeBlock[A](request: R, block: P => Future[A]): Future[A]

  /** Get the action context to run the request in. */
  protected implicit val IOActionContext = EntityIOActionContext.Implicits.global
}

trait Action[-R, +P] extends ActionFunction[R, P]
