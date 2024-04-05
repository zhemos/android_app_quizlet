package by.zm.quizlet.core.cache.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@SmallTest
class TermDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: TermDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = AppDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.termDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTerm() = runTest {
        val term = TermItem(id = 1, term = "Term", translate = "Translate", moduleId = 1)
        dao.insertTerm(term)

        val terms = dao.observeAllTerms().first()

        assertEquals(true, terms.contains(term))
    }

    @Test
    fun insertTerms() = runTest {
        val terms = listOf(
            TermItem(id = 1, term = "Term1", translate = "Translate1", moduleId = 1),
            TermItem(id = 2, term = "Term2", translate = "Translate2", moduleId = 1),
            TermItem(id = 3, term = "Term3", translate = "Translate3", moduleId = 1),
        )
        dao.insertTerms(terms)

        val allTerms = dao.observeAllTerms().first()

        assertEquals(terms.size, allTerms.size)
        assertEquals(true, terms == allTerms)
    }

    @Test
    fun deleteTerm() = runTest {
        val term = TermItem(id = 1, term = "Term", translate = "Translate", moduleId = 1)
        dao.insertTerm(term)
        dao.deleteTerm(term)

        val terms = dao.observeAllTerms().first()

        assertEquals(false, terms.contains(term))
    }

    @Test
    fun updateTerm() = runTest {
        val term = TermItem(id = 1, term = "Term", translate = "Translate", moduleId = 1)
        dao.insertTerm(term)
        val newTermItem = term.copy(translate = "New translate")
        dao.insertTerm(newTermItem)

        val terms = dao.observeAllTerms().first()

        assertEquals(true, terms.contains(newTermItem))
        assertEquals(false, terms.contains(term))
        assertEquals(1, terms.size)
    }

    @Test
    fun updateTerms() = runTest {
        val terms = listOf(
            TermItem(id = 1, term = "Term1", translate = "Translate1", moduleId = 1),
            TermItem(id = 2, term = "Term2", translate = "Translate2", moduleId = 1),
            TermItem(id = 3, term = "Term3", translate = "Translate3", moduleId = 1),
        )
        dao.insertTerms(terms)
        val newTerms = listOf(
            TermItem(id = 2, term = "Term4", translate = "Translate4", moduleId = 1),
            TermItem(id = 3, term = "Term5", translate = "Translate5", moduleId = 1),
        )
        dao.insertTerms(newTerms)

        val allTerms = dao.observeAllTerms().first()

        assertEquals(terms.size, allTerms.size)
        assertEquals(terms.first(), allTerms.first())
        assertEquals(newTerms, allTerms.subList(1, 3))
    }
}