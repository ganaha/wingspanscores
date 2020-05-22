package com.example.wingspanscores.ui.main

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Model(val context: Context) {
    /**
     * 全Player名を取得する。
     */
    fun getPlayers(): Array<String> {
        val dbHelper = WingspanDbHelper(context)
        val database = dbHelper.readableDatabase
        val cursor = database.rawQuery("""
            SELECT name FROM players ORDER BY id
        """.trimIndent(), null)
        if (cursor.count === 0) return arrayOf<String>()

        var results = arrayOf<String>()
        while (cursor.moveToNext()) {
            results += cursor.getString(0)
        }

        return results
    }

    fun insertPlayer(database: SQLiteDatabase, name: String): Int {
        val cursor = database.query("players", arrayOf("id"), "name = ?", arrayOf(name), null, null, "id ASC")
        if (cursor.count == 0) {
            val players = ContentValues()
            players.put("name", name)
            return database.insert("players", null, players).toInt()
        }
        if (cursor.moveToNext()) {
            return cursor.getInt(0)
        }
        return -1
    }

    /**
     * プレーヤー登録とスコア登録
     */
    fun insertData(name: String, birds: Int, bonus: Int, round: Int, eggs: Int, food: Int, tucked: Int, total: Int, rank: Int) {
        if (name.isNullOrBlank()) return

        val dbHelper = WingspanDbHelper(context)
        val database = dbHelper.writableDatabase

        val playerId = insertPlayer(database, name)
        if (playerId == -1) return

        val scores = ContentValues()
        scores.put("player_id", playerId)
        scores.put("birds", birds)
        scores.put("bonus", bonus)
        scores.put("round", round)
        scores.put("eggs", eggs)
        scores.put("food", food)
        scores.put("tucked", tucked)
        scores.put("total", total)
        scores.put("rank", rank)

        database.insert("scores", null, scores)
    }

    /**
     * プレーヤーごとのゲーム数と勝った数
     */
    fun getScoresByPlayer(): Array<ScoreByPlayerDto> {
        val dbHelper = WingspanDbHelper(context)
        val database = dbHelper.readableDatabase
        val cursor = database.rawQuery("""
            SELECT ps1.name, ps1.game, COALESCE(ps2.win, 0) AS win, ps3.max
                FROM (
                    SELECT p1.name AS name, COUNT(s1.id) AS game FROM players AS p1, scores AS s1
                    WHERE p1.id = s1.player_id
                    GROUP BY p1.name
                ) AS ps1, (
                    SELECT p3.name AS name, MAX(s3.total) AS max FROM players AS p3, scores AS s3
                    WHERE p3.id = s3.player_id
                    GROUP BY p3.name
                ) AS ps3
            LEFT JOIN (
                SELECT p2.name AS name, COUNT(s2.id) AS win FROM players AS p2, scores AS s2
                WHERE p2.id = s2.player_id AND s2.rank = 1
                GROUP BY p2.name
            ) AS ps2
            ON ps1.name = ps2.name
            WHERE ps1.name = ps3.name
        """.trimIndent(), null)
        if (cursor.count === 0) return arrayOf<ScoreByPlayerDto>()

        var results = arrayOf<ScoreByPlayerDto>()
        while (cursor.moveToNext()) {
            results += ScoreByPlayerDto(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3))
        }

        return results
    }

    /**
     * 特定プレーヤーのグラフ表示用スコアデータ
     */
    fun getScores(name: String): Array<ScoresDto> {
        val dbHelper = WingspanDbHelper(context)
        val database = dbHelper.readableDatabase
        val cursor = database.rawQuery("""
            SELECT p.name, s.birds, s.bonus, s.round, s.eggs, s.food, s.tucked, s.total, s.rank
            FROM players AS p, scores AS s
            WHERE p.id = s.player_id AND p.name = ?
        """.trimIndent(), arrayOf(name))
        if (cursor.count === 0) return arrayOf<ScoresDto>()

        var results = arrayOf<ScoresDto>()
        while (cursor.moveToNext()) {
            results += ScoresDto(
                cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),
                cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
                cursor.getInt(8)
            )
        }

        return results
    }
}