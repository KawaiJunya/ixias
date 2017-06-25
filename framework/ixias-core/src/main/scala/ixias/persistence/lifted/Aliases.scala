/*
 * This file is part of the IxiaS services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package ixias.persistence.lifted

/**
 * Aliases for lifted embedding features. This trait can be mixed into aliasing
 * objects which simplify the use of the lifted embedding.
 */
trait Aliases
{
  val  Cursor = ixias.persistence.model.Cursor
  type Cursor = ixias.persistence.model.Cursor
}
