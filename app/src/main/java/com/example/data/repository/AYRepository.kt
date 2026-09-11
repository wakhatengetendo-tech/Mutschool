package com.example.data.repository

import com.example.data.dao.UserProgressDao
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

class AYRepository(private val progressDao: UserProgressDao) {

    // Room DB Flow for user's completed items and notes
    val allUserProgress: Flow<List<UserProgressEntity>> = progressDao.getAllProgress()

    suspend fun toggleCompletion(id: String, type: String, title: String, category: String, currentStatus: Boolean) {
        progressDao.insertOrUpdate(
            UserProgressEntity(
                id = id,
                type = type,
                title = title,
                category = category,
                isCompleted = !currentStatus
            )
        )
    }

    suspend fun saveNote(id: String, type: String, title: String, category: String, text: String) {
        progressDao.insertOrUpdate(
            UserProgressEntity(
                id = id,
                type = type,
                title = title,
                category = category,
                noteText = text
            )
        )
    }

    suspend fun deleteProgress(id: String) {
        progressDao.deleteById(id)
    }

    // Remnant Ministry Hub Main Link
    val remnantSchoolUrl = "https://learn.remnantschool.com/"

    val remnantHubResources = listOf(
        RemnantHubResource(
            title = "Remnant Online Learning Portal",
            url = "https://learn.remnantschool.com/",
            description = "The premier digital resource hub for Youth and Children's Ministries. Interactive visual materials, Pathfinder & Adventurer class workbooks, and leader guidelines.",
            category = "Main Portal"
        ),
        RemnantHubResource(
            title = "Pathfinder Class Curriculum Manuals",
            url = "https://learn.remnantschool.com/#pathfinders",
            description = "Downloadable teaching slide decks, student activity sheets, and requirement sheets for Friend through Guide classes.",
            category = "Curriculum"
        ),
        RemnantHubResource(
            title = "Adventurer Visual Resource Bank",
            url = "https://learn.remnantschool.com/#adventurers",
            description = "Coloring pages, memory verse cards, and award tracking charts for Busy Bee, Sunbeam, Builder, and Helping Hand.",
            category = "Adventurers"
        ),
        RemnantHubResource(
            title = "Master Guide Leadership Training",
            url = "https://learn.remnantschool.com/#masterguide",
            description = "Leadership modules, staff orientation, drill instructor handbooks, and campout safety guidelines.",
            category = "Leadership"
        )
    )

    // Pledges, Aims & Laws Data
    val pledgesList = listOf(
        AYPledge(
            id = "ay_aim",
            title = "AY Aim",
            category = "AY",
            text = "The Advent Message to All the World in This Generation.",
            explanation = "A bold declaration of mission: sharing the saving truth of Jesus' second coming with urgency, compassion, and global reach."
        ),
        AYPledge(
            id = "ay_motto",
            title = "AY Motto",
            category = "AY",
            text = "The Love of Christ Compelleth Us.",
            explanation = "2 Corinthians 5:14 — Christ's selfless love is our primary motivation for service, fellowship, and witnessing."
        ),
        AYPledge(
            id = "ay_pledge",
            title = "AY Pledge",
            category = "AY",
            text = "Loving the Lord Jesus, I promise to take an active part in the work of the Adventist Youth Society, doing what I can to help others and to finish the work of the Gospel in all the world.",
            explanation = "A personal commitment to active ministry participation, loving service to neighbors, and spreading the Gospel."
        ),
        AYPledge(
            id = "pf_pledge",
            title = "Pathfinder Pledge",
            category = "Pathfinder",
            text = "By the grace of God, I will be pure and kind and true. I will keep the Pathfinder Law. I will be a servant of God and a friend to man.",
            explanation = "Daily spiritual commitment relying on God's strength to live an honorable, Christlike life of service."
        ),
        AYPledge(
            id = "pf_law",
            title = "Pathfinder Law",
            category = "Pathfinder",
            text = "1. Keep the morning watch.\n2. Do my honest part.\n3. Care for my body.\n4. Keep a level eye.\n5. Be courteous and obedient.\n6. Walk softly in the sanctuary.\n7. Keep a song in my heart.\n8. Go on God's errands.",
            explanation = "Eight practical principles governing personal devotion, health, reverence, integrity, and readiness for service."
        ),
        AYPledge(
            id = "adv_pledge",
            title = "Adventurer Pledge",
            category = "Adventurer",
            text = "Because Jesus loves me, I will always do my best.",
            explanation = "Focuses on Jesus' foundational love as the reason for offering our best effort in home, school, and church."
        ),
        AYPledge(
            id = "adv_law",
            title = "Adventurer Law",
            category = "Adventurer",
            text = "Jesus can help me to: Be obedient, Be pure, Be true, Be kind, Be respectful, Be attentive, Be helpful, Be cheerful, Be thoughtful, Be reverent.",
            explanation = "Ten child-friendly virtues practiced with the help of Jesus every single day."
        ),
        AYPledge(
            id = "legion_of_honor",
            title = "AY Legion of Honor",
            category = "AY",
            text = "I volunteer now to join the AY Legion of Honor, and by the grace of God I will:\n- Honor Christ in my thoughts, words, and deeds.\n- Observe the morning watch of prayer and Bible study.\n- Avoid toxic substances and keep my body temple clean.\n- Take an active part in church and youth ministry work.",
            explanation = "High spiritual standard for senior youth and leaders dedicating their lives to moral purity and Christian witness."
        )
    )

    // AY & Pathfinder Songs
    val songsList = listOf(
        AYSong(
            id = "pf_song",
            title = "Pathfinder Song",
            group = "Pathfinders",
            lyrics = """
                Oh, we are the Pathfinders strong,
                The servants of God are we.
                Faithful as we march along,
                In kindness, truth and purity.
                
                We're building a message to tell to the world,
                A truth that will set us free:
                King Jesus the Savior's coming back
                For you and me!
            """.trimIndent()
        ),
        AYSong(
            id = "ay_anthem",
            title = "Advent Youth Anthem",
            group = "AY Youth",
            lyrics = """
                Advent Youth arise! Lift your eyes to the skies,
                Jesus comes in power and glory!
                Bound by His love, with power from above,
                Tell the world salvation's story.
                
                Stand for the truth, O noble youth!
                Christ is our strength and banner high;
                Marching in light till faith turns to sight,
                When our Lord returns in the sky!
            """.trimIndent()
        ),
        AYSong(
            id = "adv_song",
            title = "Adventurer Song",
            group = "Adventurers",
            lyrics = """
                We are Adventurers, having fun as we march along,
                Singing a song of Jesus' love, happy and brave and strong!
                Light of the world, shining bright for Jesus every day,
                We are Adventurers, following Jesus all the way!
            """.trimIndent()
        )
    )

    // Ministry Classes
    val ministryLevels = listOf(
        // Pathfinders
        MinistryLevel(
            id = "pf_friend",
            name = "Friend",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 5 / Age 10",
            colorHex = 0xFF2563EB,
            description = "Foundational Pathfinder class focusing on personal devotion, outdoor skills, and basic Christian service.",
            requirements = listOf(
                RequirementItem("fr_1", "General", "Age & Enrollment", "Be 10 years old or in Grade 5, and an active member of the Pathfinder Club."),
                RequirementItem("fr_2", "Spiritual", "Morning Watch & Memory Verses", "Memorize and explain the Pathfinder Pledge and Law, and complete the Friend memory verse list."),
                RequirementItem("fr_3", "Service", "Community Good Turn", "Participate in at least two community service projects or church outreach events."),
                RequirementItem("fr_4", "Fitness", "Basic First Aid & Health", "Demonstrate basic first aid treatment for cuts, burns, insect bites, and nosebleeds."),
                RequirementItem("fr_5", "Nature", "Knot Tying & Campcraft", "Tie 6 basic knots (Square, Bowline, Clove Hitch, Sheet Bend, Taut-line, Figure Eight) and build a campfire safely.")
            )
        ),
        MinistryLevel(
            id = "pf_companion",
            name = "Companion",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 6 / Age 11",
            colorHex = 0xFF0284C7,
            description = "Builds companionship skills, deeper Bible study, hiking, and camping techniques.",
            requirements = listOf(
                RequirementItem("cp_1", "General", "Club Participation", "Be 11 years old or in Grade 6 and completeCompanion reading certificate."),
                RequirementItem("cp_2", "Spiritual", "Gospel Study", "Read the Gospel of Mark and discuss key parables and miracles of Jesus."),
                RequirementItem("cp_3", "Service", "Friendship & Ministry", "Organize a friendship gathering or assist an elderly neighbor."),
                RequirementItem("cp_4", "Outdoor", "Overnight Campout", "Participate in an overnight campout, set up a tent, and prepare camp meals.")
            )
        ),
        MinistryLevel(
            id = "pf_explorer",
            name = "Explorer",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 7 / Age 12",
            colorHex = 0xFF059669,
            description = "Focuses on wilderness exploration, nature preservation, physical endurance, and church leadership.",
            requirements = listOf(
                RequirementItem("ex_1", "General", "Physical Fitness Test", "Pass physical fitness benchmarks in running, pushups, and agility."),
                RequirementItem("ex_2", "Spiritual", "Church History & Heritage", "Study the origins of the Adventist Youth movement and early pioneers."),
                RequirementItem("ex_3", "Nature", "Astronomy or Trees Honor", "Earn the Stars Honor or Trees Honor through field identification.")
            )
        ),
        MinistryLevel(
            id = "pf_ranger",
            name = "Ranger",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 8 / Age 13",
            colorHex = 0xFFD97706,
            description = "Advanced wilderness survival, navigation, emergency preparedness, and personal discipline.",
            requirements = listOf(
                RequirementItem("rg_1", "General", "Leadership Role", "Serve as Squad Leader or Assistant Unit Captain for 3 months."),
                RequirementItem("rg_2", "Outdoor", "Wilderness Survival", "Construct an emergency wilderness shelter and spend a night in it.")
            )
        ),
        MinistryLevel(
            id = "pf_voyager",
            name = "Voyager",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 9 / Age 14",
            colorHex = 0xFF7C3AED,
            description = "Deep spiritual discernment, youth evangelism, career planning, and mentoring younger Pathfinders.",
            requirements = listOf(
                RequirementItem("vy_1", "Spiritual", "Christian Life & Ethics", "Lead a youth Sabbath School discussion or vespers program."),
                RequirementItem("vy_2", "Outdoor", "5-Mile Hike & Orienteering", "Complete a 5-mile wilderness hike using a magnetic compass and topographical map.")
            )
        ),
        MinistryLevel(
            id = "pf_guide",
            name = "Guide",
            ministryGroup = "Pathfinders",
            ageGroup = "Grade 10 / Age 15",
            colorHex = 0xFFDC2626,
            description = "The capstone Pathfinder class preparing youth for Master Guide leadership and lifelong service.",
            requirements = listOf(
                RequirementItem("gd_1", "General", "Mastery of Club Skills", "Demonstrate mastery in drill & marching commands, knot tying, and camp safety."),
                RequirementItem("gd_2", "Service", "Evangelistic Project", "Plan and execute a youth evangelism or community service outreach project.")
            )
        ),
        // Adventurers
        MinistryLevel(
            id = "adv_busy_bee",
            name = "Busy Bee",
            ministryGroup = "Adventurers",
            ageGroup = "Grade 1 / Age 6",
            colorHex = 0xFFEAB308,
            description = "Early childhood ministry encouraging obedience, helpfulness, and curiosity about God's creation.",
            requirements = listOf(
                RequirementItem("bb_1", "Basic", "Pledge & Law", "Recite the Adventurer Pledge and Law with understanding."),
                RequirementItem("bb_2", "My God", "God's Plan to Save Me", "Create a Bible story picture book showing Jesus' love for children.")
            )
        ),
        MinistryLevel(
            id = "adv_sunbeam",
            name = "Sunbeam",
            ministryGroup = "Adventurers",
            ageGroup = "Grade 2 / Age 7",
            colorHex = 0xFFF97316,
            description = "Spreading warmth, kindness, good manners, and health habits at home and school.",
            requirements = listOf(
                RequirementItem("sb_1", "My Self", "Fitness & Hygiene", "Demonstrate proper handwashing, teeth brushing, and healthy snack choices.")
            )
        ),
        MinistryLevel(
            id = "adv_builder",
            name = "Builder",
            ministryGroup = "Adventurers",
            ageGroup = "Grade 3 / Age 8",
            colorHex = 0xFF10B981,
            description = "Building strong Christian character, family relationships, and outdoor appreciation.",
            requirements = listOf(
                RequirementItem("bd_1", "My Family", "Family Helper", "Complete a 1-week chore chart assisting parents cheerfully at home.")
            )
        ),
        MinistryLevel(
            id = "adv_helping_hand",
            name = "Helping Hand",
            ministryGroup = "Adventurers",
            ageGroup = "Grade 4 / Age 9",
            colorHex = 0xFF8B5CF6,
            description = "Senior Adventurer class emphasizing practical Christian service and readiness for Pathfinders.",
            requirements = listOf(
                RequirementItem("hh_1", "My World", "Community Helper", "Participate in a neighborhood clean-up or visit a nursing home.")
            )
        ),
        // Master Guide
        MinistryLevel(
            id = "master_guide",
            name = "Master Guide",
            ministryGroup = "Master Guide",
            ageGroup = "Age 16+ / Adults",
            colorHex = 0xFF1E3A8A,
            description = "Highest level of leadership in Adventurer and Pathfinder youth ministry, training leaders for global ministry.",
            requirements = listOf(
                RequirementItem("mg_1", "Prerequisite", "Active Membership & Recommendation", "Be a baptized SDA church member with local church board approval."),
                RequirementItem("mg_2", "Personal Devotion", "Devotional Life & Spirit of Prophecy", "Complete the Encounter reading plan and read 'Steps to Christ' and 'Education'."),
                RequirementItem("mg_3", "Skill Development", "Standard Honors & Drill Instruction", "Hold 10 active AY Honors including First Aid, Knot Tying, and Drill & Marching."),
                RequirementItem("mg_4", "Child Development", "Seminar & Workshops", "Attend conference-sponsored seminars on child psychology, discipline, and safety regulations.")
            )
        )
    )

    // AY Honors Catalog
    val honorsList = listOf(
        HonorItem(
            id = "h_knot_tying",
            name = "Knot Tying",
            category = "Recreation",
            skillLevel = 1,
            summary = "Master 20 essential knots, hitches, and bends used in camping, rescue, and everyday pioneering.",
            requirements = listOf(
                "Define rope, cord, line, whipping, splicing, standing end, and working end.",
                "Demonstrate proper care and storage of natural fiber and synthetic rope.",
                "Tie at least 15 standard knots from memory including Square, Bowline, Clove Hitch, Sheet Bend, and Taut-line.",
                "Demonstrate whipping the end of a rope to prevent fraying."
            )
        ),
        HonorItem(
            id = "h_camping_skills_1",
            name = "Camping Skills I",
            category = "Recreation",
            skillLevel = 1,
            summary = "Essential field craft for first-time Pathfinder campers.",
            requirements = listOf(
                "Explain 8 camp safety rules and environmental stewardship principles.",
                "Demonstrate pitching, striking, and folding a 2-person tent.",
                "Build a teepee or log-cabin fire outdoors and extinguish it completely.",
                "Participate in a weekend campout."
            )
        ),
        HonorItem(
            id = "h_first_aid_basic",
            name = "First Aid - Basic",
            category = "Health & Science",
            skillLevel = 1,
            summary = "Emergency response protocols for cuts, burns, fractures, and shock.",
            requirements = listOf(
                "Demonstrate the ABCs of first aid (Airway, Breathing, Circulation).",
                "Apply proper pressure bandages for arterial and venous bleeding.",
                "Describe treatment for heat exhaustion, hypothermia, and snake bites.",
                "Assemble a standard personal first aid kit."
            )
        ),
        HonorItem(
            id = "h_stars",
            name = "Stars & Astronomy",
            category = "Nature",
            skillLevel = 1,
            summary = "Discover God's handiwork in the night sky and major constellations.",
            requirements = listOf(
                "Locate Polaris (the North Star) using the Big Dipper pointer stars.",
                "Identify at least 10 major constellations and 5 planets.",
                "Explain the cause of lunar phases and eclipses.",
                "Quote Psalm 19:1 regarding God's glory in the heavens."
            )
        ),
        HonorItem(
            id = "h_christian_storytelling",
            name = "Christian Storytelling",
            category = "Spiritual",
            skillLevel = 1,
            summary = "Master the art of engaging children with moral and biblical stories.",
            requirements = listOf(
                "Outline 4 key principles of effective oral storytelling.",
                "Prepare and tell 2 Bible stories to an audience of Adventurers or children.",
                "Use visual aids or props effectively during narrative delivery."
            )
        ),
        HonorItem(
            id = "h_trees",
            name = "Trees & Shrubs",
            category = "Nature",
            skillLevel = 1,
            summary = "Field identification of native trees by leaves, bark, and seeds.",
            requirements = listOf(
                "Collect, press, and identify leaves from 15 different species of trees.",
                "Distinguish between deciduous and evergreen coniferous trees.",
                "Describe the economic and ecological importance of forest conservation."
            )
        )
    )

    // Knot Tying Guide
    val knotsGuide = listOf(
        KnotGuideItem(
            id = "k_square",
            name = "Square Knot (Reef Knot)",
            category = "Joining",
            description = "Classic joining knot for binding two ropes of equal diameter.",
            primaryUse = "First aid bandaging, tying packages, sail reefing.",
            steps = listOf(
                "Take the right working end and cross it over the left end, then tuck it under.",
                "Take the same end (now on the left) and cross it over the right end.",
                "Tuck it through the loop and pull both standing ends firmly to tighten.",
                "Memory mnemonic: 'Right over left and under, then left over right and under.'"
            )
        ),
        KnotGuideItem(
            id = "k_bowline",
            name = "Bowline (King of Knots)",
            category = "Loop",
            description = "Creates a secure fixed loop at the end of a rope that never slips or jams under load.",
            primaryUse = "Wilderness rescue, hoisting heavy equipment, boat mooring.",
            steps = listOf(
                "Form a small loop ('the rabbit hole') near the standing end.",
                "Pass the working end ('the rabbit') up through the hole.",
                "Pass the rabbit around behind the standing end ('the tree').",
                "Pass the rabbit back down into the hole and pull tight.",
                "Memory mnemonic: 'The rabbit comes out of the hole, goes around the tree, and back into the hole.'"
            )
        ),
        KnotGuideItem(
            id = "k_clove_hitch",
            name = "Clove Hitch",
            category = "Hitch",
            description = "Fast, reliable hitch to secure a rope to a cylindrical post or spar.",
            primaryUse = "Starting and ending pioneering lashings, tying guy lines to stakes.",
            steps = listOf(
                "Wrap the working end around the post.",
                "Cross over the standing line to make a second wrap in the same direction.",
                "Tuck the working end under the second wrap and pull tight."
            )
        ),
        KnotGuideItem(
            id = "k_sheet_bend",
            name = "Sheet Bend",
            category = "Joining",
            description = "The premier knot for joining two ropes of unequal thickness.",
            primaryUse = "Extending short ropes, connecting thick camp lines to thin guy cords.",
            steps = listOf(
                "Form a bight (U-bend) in the thicker rope.",
                "Pass the thinner rope up through the bight.",
                "Wrap the thinner rope around the back of both sides of the bight.",
                "Tuck the thinner rope under its own standing part, not into the bight itself.",
                "Pull firmly to set."
            )
        ),
        KnotGuideItem(
            id = "k_tautline",
            name = "Taut-Line Hitch",
            category = "Hitch",
            description = "Adjustable friction hitch that slides easily to change tension, then grips securely.",
            primaryUse = "Tent guy line tensioning, tiedowns for vehicle loads.",
            steps = listOf(
                "Wrap the working end twice around the inside of the loop towards the anchor.",
                "Pass the working end across the standing line to the outside of the loop.",
                "Make one final wrap around the standing line and tuck it through the loop to form a half hitch.",
                "Pull tight to secure the friction grip."
            )
        ),
        KnotGuideItem(
            id = "k_fig8",
            name = "Figure Eight Knot",
            category = "Stopper",
            description = "Distinctive figure-8 shape stopper knot that prevents rope ends from unreeving.",
            primaryUse = "Sailing blocks, climbing harnesses, stopper knot.",
            steps = listOf(
                "Form a loop in the rope.",
                "Pass the working end around the standing line behind the loop.",
                "Tuck the working end through the loop from the front and pull tight."
            )
        )
    )

    // Drill & Marching Commands
    val drillCommands = listOf(
        DrillCommand(
            id = "d_fallin",
            command = "Squad... FALL IN!",
            response = "Quick response into parade formation",
            category = "Stationary",
            description = "Units quickly line up in rank order at attention, extending left arm for proper interval alignment."
        ),
        DrillCommand(
            id = "d_attention",
            command = "Squad... ATTENTION!",
            response = "Snap feet together, posture erect, eyes straight ahead",
            category = "Stationary",
            description = "Heels together at a 45-degree angle, knees straight, chest lifted, arms relaxed along sides with thumbs along trouser seams."
        ),
        DrillCommand(
            id = "d_ease",
            command = "Parade... REST!",
            response = "Left foot steps 12 inches to the left, hands crossed behind back",
            category = "Stationary",
            description = "Move left foot smartly 12 inches left. Place right hand in palm of left hand behind back, fingers flat. Remain motionless and silent."
        ),
        DrillCommand(
            id = "d_rightface",
            command = "Right... FACE!",
            response = "Pivot 90 degrees right on right heel and left toe",
            category = "Stationary",
            description = "On the command of execution FACE, turn 90 degrees right pivoting on right heel and ball of left foot. Bring left heel smartly alongside right."
        ),
        DrillCommand(
            id = "d_aboutface",
            command = "About... FACE!",
            response = "Pivot 180 degrees to the rear",
            category = "Stationary",
            description = "Move toe of right foot half shoe-length behind and slightly left of left heel. Pivot 180 degrees clockwise on left heel and right ball."
        ),
        DrillCommand(
            id = "d_forwardmarch",
            command = "Forward... MARCH!",
            response = "Step off with the left foot",
            category = "Marching",
            description = "Step off cleanly with left foot taking a full 30-inch step at 120 steps per minute, swinging arms naturally."
        ),
        DrillCommand(
            id = "d_halt",
            command = "Squad... HALT!",
            response = "Take one additional step and bring rear foot to attention",
            category = "Marching",
            description = "Command given on either foot. Take one more full step with the opposite foot, then bring rear foot sharply alongside."
        )
    )
}
