package by.zm.quizlet.core.cache.room.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.cache.room.model.TermItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class TermDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: TermDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.termDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTerm() = runTest {
        val term = TermItem(id = 1, title = "Term", translate = "Translate", imageUrl = "", isFavourites = false, moduleId = 1)
        dao.insertTerm(term)

        val terms = dao.observeAllTermsByModuleId(1).first()

        assertEquals(true, terms.contains(term))
    }

    @Test
    fun insertTerms() = runTest {
        val terms = listOf(
            TermItem(id = 1, title = "Term1", translate = "Translate1", imageUrl = "", isFavourites = false, moduleId = 1),
            TermItem(id = 2, title = "Term2", translate = "Translate2", imageUrl = "", isFavourites = false, moduleId = 1),
            TermItem(id = 3, title = "Term3", translate = "Translate3", imageUrl = "", isFavourites = false, moduleId = 1),
        )
        dao.insertTerms(terms)

        val allTerms = dao.observeAllTermsByModuleId(1).first()

        assertEquals(terms.size, allTerms.size)
        assertEquals(true, terms == allTerms)
    }

    @Test
    fun deleteTerm() = runTest {
        val term = TermItem(id = 1, title = "Term", translate = "Translate", imageUrl = "", isFavourites = false, moduleId = 1)
        dao.insertTerm(term)
        dao.deleteTerm(term)

        val terms = dao.observeAllTermsByModuleId(1).first()

        assertEquals(false, terms.contains(term))
    }

    @Test
    fun updateTerm() = runTest {
        val term = TermItem(id = 1, title = "Term", translate = "Translate", imageUrl = "", isFavourites = false, moduleId = 1)
        dao.insertTerm(term)
        val newTermItem = term.copy(translate = "New translate")
        dao.insertTerm(newTermItem)

        val terms = dao.observeAllTermsByModuleId(1).first()

        assertEquals(true, terms.contains(newTermItem))
        assertEquals(false, terms.contains(term))
        assertEquals(1, terms.size)
    }

    @Test
    fun updateTerms() = runTest {
        val terms = listOf(
            TermItem(id = 1, title = "Term1", translate = "Translate1", imageUrl = "", isFavourites = false, moduleId = 1),
            TermItem(id = 2, title = "Term2", translate = "Translate2", imageUrl = "", isFavourites = false, moduleId = 1),
            TermItem(id = 3, title = "Term3", translate = "Translate3", imageUrl = "", isFavourites = false, moduleId = 1),
        )
        dao.insertTerms(terms)
        val newTerms = listOf(
            TermItem(id = 2, title = "Term4", translate = "Translate4", imageUrl = "", isFavourites = false, moduleId = 1),
            TermItem(id = 3, title = "Term5", translate = "Translate5", imageUrl = "", isFavourites = false, moduleId = 1),
        )
        dao.insertTerms(newTerms)

        val allTerms = dao.observeAllTermsByModuleId(1).first()

        assertEquals(terms.size, allTerms.size)
        assertEquals(terms.first(), allTerms.first())
        assertEquals(newTerms, allTerms.subList(1, 3))
    }
}