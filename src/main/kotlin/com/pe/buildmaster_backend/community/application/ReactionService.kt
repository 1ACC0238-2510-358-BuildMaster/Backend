package com.pe.buildmaster_backend.community.application

import com.pe.buildmaster_backend.community.domain.interfaces.PostRepository
import com.pe.buildmaster_backend.community.domain.interfaces.ReactionRepository
import com.pe.buildmaster_backend.community.domain.model.commands.AddReactionCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReactionService(
    private val reactionRepository: ReactionRepository,
    private val postRepository: PostRepository // Para validar que el post existe
) {

    suspend fun addOrUpdateReaction(command: AddReactionCommand): Reaction {
        // 1. Validar que el post existe
        postRepository.findById(command.postId)
            ?: throw NoSuchElementException("Post with id ${command.postId.value} not found. Cannot add reaction.")

        // 2. Verificar si ya existe una reacción del mismo tipo por el mismo usuario para este post
        val existingReaction = reactionRepository.findByPostIdAndUserIdAndType(
            command.postId,
            command.userId,
            command.reactionType
        )

        if (existingReaction != null) {
            // Si la reacción es la misma, podríamos no hacer nada o considerarlo un error,
            // o si se permite "quitar" una reacción volviendo a hacer clic, se manejaría aquí.
            // Por ahora, si existe y es del mismo tipo, simplemente la devolvemos (o podríamos lanzar error).
            // Si la idea es que un "like" repetido quita el "like", la lógica sería:
            // reactionRepository.delete(existingReaction)
            // return existingReaction // O un estado que indique que se eliminó.
            // Para este ejemplo, asumamos que una reacción del mismo tipo no cambia nada o actualiza el timestamp.
            // O, más simple, que no se permite (se debería quitar primero).

            // Vamos a adoptar una lógica de "upsert" implícita o de "toggle" para tipos como LIKE/DISLIKE.
            // Si es un REPOST, usualmente no se "des-repostea" de la misma manera.
            // Por simplicidad: si ya existe una reacción de este tipo, no hacemos nada o la eliminamos.
            // Vamos a optar por eliminarla si es exactamente la misma (como un toggle de like).
            // Si la nueva reacción es DIFERENTE (ej. era LIKE ahora es DISLIKE), eliminamos la vieja y creamos la nueva.

            // Lógica de toggle para LIKE/DISLIKE:
            if (command.reactionType == ReactionType.LIKE || command.reactionType == ReactionType.DISLIKE) {
                if (existingReaction.type == command.reactionType) {
                    // Mismo tipo, la eliminamos (toggle off)
                    reactionRepository.delete(existingReaction)
                    // Podríamos devolver un estado especial o la reacción eliminada.
                    // Para simplificar, podríamos lanzar una excepción o devolver null
                    // indicando que la reacción fue removida.
                    // O, para este ejemplo, simplemente no permitir añadirla de nuevo.
                    // Vamos a cambiar: si ya existe, la borramos y no añadimos una nueva (efecto toggle)
                    reactionRepository.delete(existingReaction)
                    // Necesitamos una forma de indicar que la reacción fue eliminada.
                    // Por ahora, este método siempre intentará crear una si no existe o si el tipo es diferente.
                    // Así que si el tipo es el mismo y existe, se borra.
                    // Esta lógica se puede refinar.
                    //
                    // Simplificación: si existe una reacción del mismo tipo, la eliminamos.
                    // Si el usuario quiere cambiar de LIKE a DISLIKE, primero debe quitar LIKE, luego añadir DISLIKE.
                    // O el servicio maneja el cambio.
                    //
                    // Estrategia: Si el usuario intenta añadir una reacción del mismo tipo, la eliminamos.
                    // Si es un tipo diferente, eliminamos las otras (LIKE/DISLIKE) y añadimos la nueva.
                    // REPOST es especial y podría acumularse o tener reglas diferentes.

                    // Estrategia más simple para este ejemplo:
                    // Si una reacción del mismo tipo ya existe, la eliminamos (toggle off).
                    // Si es un tipo diferente, eliminamos cualquier LIKE/DISLIKE existente y añadimos la nueva.
                    // REPOST simplemente se añade si no existe.

                    if (existingReaction.type == command.reactionType) {
                        reactionRepository.delete(existingReaction)
                        // ¿Qué devolver aquí? Podríamos devolver la reacción eliminada o null.
                        // O lanzar una excepción si se espera que el comando siempre cree algo.
                        // Por ahora, vamos a asumir que el comando es para "establecer" una reacción.
                        // Si es la misma, la borramos. Si es diferente, borramos la vieja y ponemos la nueva.
                        throw IllegalStateException("Reaction of type ${command.reactionType} already exists and was removed. Re-issue command if you wish to change to a different type.")
                    }
                }
                // Si es LIKE o DISLIKE y había una del otro tipo, la eliminamos
                val otherToggleType = if (command.reactionType == ReactionType.LIKE) ReactionType.DISLIKE else ReactionType.LIKE
                reactionRepository.findByPostIdAndUserIdAndType(command.postId, command.userId, otherToggleType)?.let {
                    reactionRepository.delete(it)
                }
            } else if (command.reactionType == ReactionType.REPOST && existingReaction != null) {
                // Si ya existe un REPOST, no permitimos otro (o lo eliminamos)
                reactionRepository.delete(existingReaction) // Toggle para repost también
                throw IllegalStateException("Repost already exists and was removed.")
            }
        }


        val reaction = Reaction(
            // id se autogenera en la entidad Reaction
            postId = command.postId,
            userId = command.userId,
            type = command.reactionType,
            createdAt = LocalDateTime.now()
        )
        return reactionRepository.save(reaction)
    }

    suspend fun removeReaction(postId: PostId, userId: UserId, reactionType: ReactionType) {
        val reaction = reactionRepository.findByPostIdAndUserIdAndType(postId, userId, reactionType)
            ?: throw NoSuchElementException("Reaction of type $reactionType by user ${userId.value} on post ${postId.value} not found.")

        reactionRepository.delete(reaction)
    }

    suspend fun getReactionsForPost(postId: PostId): List<Reaction> {
        postRepository.findById(postId)
            ?: throw NoSuchElementException("Post with id ${postId.value} not found.")
        return reactionRepository.findByPostId(postId)
    }
}