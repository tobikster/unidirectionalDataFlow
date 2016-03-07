package com.odai.architecturedemo.persistence

import com.odai.architecturedemo.model.Cat
import com.odai.architecturedemo.model.Cats
import com.odai.architecturedemo.model.FavouriteCats
import com.odai.architecturedemo.model.FavouriteState
import rx.Observable
import java.util.concurrent.TimeUnit

class InMemoryCatRepo : CatRepository {

    var cats: Cats = Cats(listOf(Cat("Foo")))
    var favouriteCats: FavouriteCats = FavouriteCats(mapOf())

    override fun saveCats(cats: Cats) {
        this.cats = cats
    }

    override fun readCats(): Observable<Cats> {
        if (cats.isEmpty()) {
            return Observable.empty()
        } else {
            return Observable.just(cats).delay(1, TimeUnit.SECONDS)
        }
    }

    override fun readFavouriteCats(): Observable<FavouriteCats> {
        if (favouriteCats.isEmpty()) {
            return Observable.empty()
        } else {
            return Observable.just(favouriteCats).delay(1, TimeUnit.SECONDS)
        }
    }

    override fun saveFavouriteCats(cats: FavouriteCats) {
        favouriteCats = cats
    }

    override fun saveCatFavoriteStatus(it: Pair<Cat, FavouriteState>) {
        favouriteCats = favouriteCats.put(it)
    }

}
