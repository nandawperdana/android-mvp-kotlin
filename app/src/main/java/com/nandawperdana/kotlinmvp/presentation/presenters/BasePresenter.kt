package com.nandawperdana.kotlinmvp.presentation.presenters

/**
 * Created by nandawperdana.
 */
interface BasePresenter {

    /**
     *
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onAttach() method.
     */
    fun onAttach()

    /**
     *
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDetach() method.
     */
    fun onDetach()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    fun resume()

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    fun pause()

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    fun stop()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    fun destroy()

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    fun onError(message: String)
}
