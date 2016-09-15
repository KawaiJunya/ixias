/*
 * This file is part of the IxiaS services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package ixias.play.api.auth.mvc

import play.api.Application
import play.api.mvc.{ Result, Results }

import scala.concurrent.Future
import ixias.play.api.mvc.{ StackActionBuilder, StackActionRequest }

/**
 * Provides the custom action for authentication.
 */
object AuthenticatedOrNot extends StackActionBuilder with Results {

  /**
   * Authenticate user's session.
   */
  def invokeBlock[A](request: StackActionRequest[A], block: StackActionRequest[A] => Future[Result]): Future[Result] = {
    implicit val ctx = executionContext
     for {
      auth <- instanceOf(classOf[AuthProfile])
      v    <- auth.restore(request) flatMap {
        case (None,       updater) => block(request).map(updater)
        case (Some(user), updater) => block {
           request.set(auth.UserKey, user)
        } map(updater)
      }
    } yield v
  }
}
